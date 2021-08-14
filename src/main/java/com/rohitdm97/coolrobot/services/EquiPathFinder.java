package com.rohitdm97.coolrobot.services;

import com.rohitdm97.coolrobot.external.distance.GooglePathResponse;
import com.rohitdm97.coolrobot.external.distance.PathFinderService;
import com.rohitdm97.coolrobot.path.Path;
import com.rohitdm97.coolrobot.path.Point;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EquiPathFinder {

    private final int stepSize = 50;
    private final PathFinderService pathFinderService;

    public Path findPath(Point origin, Point destination) {
        List<Point> points = new ArrayList<>();
        points.add(origin);
        int lastRemained = 0;
        GooglePathResponse response = pathFinderService.findPath(origin, destination);
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
        return new Path(points);
    }

    /**
     * Addes the step into points list when there is some distance remained in last step addition
     * Returns the remaining distance
     */
    private int add(List<Point> points, GooglePathResponse.Step step, int lastRemained) {
        int thisStepSize = step.getDistance().getMeters() + lastRemained;
        if (thisStepSize == stepSize) {
            points.add(step.getEndLocation());
            return 0;
        }
        if (thisStepSize > stepSize) {
            int midDist = stepSize - lastRemained;
            while (thisStepSize > stepSize) {
                points.add(interpolate(step.getStartLocation(), step.getEndLocation(), midDist));
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
    private Point interpolate(Point p1, Point p2, int x) {
        double l1 = p2.getLat() - p1.getLat();
        double l2 = p2.getLng() - p1.getLng();
        double d = Math.sqrt(l1 * l1 + l2 * l2);
        return Point.of(p1.getLat() + x * l1 / d, p1.getLng() + x * l2 / d);
    }

}
