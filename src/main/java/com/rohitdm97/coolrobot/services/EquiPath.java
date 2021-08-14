package com.rohitdm97.coolrobot.services;


import com.rohitdm97.coolrobot.external.distance.GooglePathResponse;
import com.rohitdm97.coolrobot.path.Path;
import com.rohitdm97.coolrobot.path.Point;
import com.rohitdm97.coolrobot.path.PolyLine;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
public class EquiPath {
    final Point origin;
    final Point destination;
    final int stepSize;
    final GooglePathResponse response;

    int lastRemained = 0;
    private final List<Point> points = new ArrayList<>();

    public EquiPath build() {
        points.add(origin);
        // assuming route exists, should select different routes?
        GooglePathResponse.Route route = response.getRoutes().get(0);
        for (GooglePathResponse.Leg leg : route.getLegs()) {
            for (GooglePathResponse.Step step : leg.getSteps()) {
                lastRemained = add(points, step, lastRemained);
            }
        }
        if (lastRemained > 0) {
            // some distance is remained, add destination
            points.add(destination);
        }
        return this;
    }

    public Path getPath() {
        return new Path(points);
    }


    /**
     * Addes the step into points list when there is some distance remained in last step addition
     * Returns the remaining distance
     */
    private int add(List<Point> points, GooglePathResponse.Step step, int lastRemained) {
        PolyLine polyLine = new PolyLine(step.getPolyline().getPoints());
        int thisStepSize = step.getDistance().getMeters() + lastRemained;
        if (thisStepSize == stepSize) {
            points.add(step.getEndLocation());
            return 0;
        }
        if (thisStepSize > stepSize) {
            int midDist = stepSize - lastRemained;
            while (thisStepSize > stepSize) {
                Point interpolated = interpolate(step.getStartLocation(), step.getEndLocation(), midDist, step.getDistance().getMeters());
                points.add(map(interpolated, polyLine));
                thisStepSize -= stepSize;
                midDist += stepSize;
            }
            return thisStepSize;
        }
        // thisStepSize < stepSize, use it in next step
        return thisStepSize;
    }

    /**
     * Returns point between two points at x distance from p1
     * x must be less than distance between p1 p2
     */
    private Point interpolate(Point p1, Point p2, double x, double actualD) {
        double l1 = p2.getLat() - p1.getLat();
        double l2 = p2.getLng() - p1.getLng();
        // using actual distance for conversion from lat long to meters
        return Point.of(p1.getLat() + l1 * x / actualD, p1.getLng() + x * l2 / actualD);
    }

    public Point map(Point point, PolyLine poly) {
        Iterator<Point> it = poly.getPoints().iterator();
        if (!it.hasNext()) return point;
        Point last = it.next();
        if (!it.hasNext()) return point;
        while (it.hasNext()) {
            Point segEnd = it.next();
            Point check = check(point, last, segEnd);
            if(check != null) {
                return check;
            }
            last = segEnd;
        }
        // no match? return original
        return point;
    }

    private Point check(Point o, Point p1, Point p2) {
        double div = p2.getLat() - p1.getLat();
        if(Math.abs(div) < 1e-6) {
            // vertical line, nothing to map
            return o;
        }
        if(div < 0) {
           return check(o, p2, p1);
        }
//         p1.getlat < p2.getlat
        if(o.getLat() >= p1.getLat() && o.getLat() <= p2.getLat()) {
            // o.getLat, y
            // y.lng - p1.lng / o.lat - p1.lat = p2.lng - p1.lng / p2.lat - p1.lat
            // y.lng - p1.lng * (p2.lat - p1.lat) = (p2.lng - p1.lng) * (o.lat - p1.lat)
            double rightSide = (p2.getLng() - p1.getLng()) * (o.getLat() - p1.getLat());
            double y = p1.getLng() + (rightSide / div);
            return Point.of(o.getLat(), y);
        }
        return null;
    }

    /**
     * Maps the point in between p1 and p2 by using lat diff
     */
    private Point map(Point o, Point p1, Point p2) {
        double L = p2.getLat() - p1.getLat();
        double x = o.getLat() - p1.getLat();
        return interpolate(p1, p2, x, L);
    }

}
