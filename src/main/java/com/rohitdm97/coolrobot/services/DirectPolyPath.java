package com.rohitdm97.coolrobot.services;

import com.rohitdm97.coolrobot.external.distance.GooglePathResponse;
import com.rohitdm97.coolrobot.path.Path;
import com.rohitdm97.coolrobot.path.Point;
import com.rohitdm97.coolrobot.path.PolyLine;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DirectPolyPath {
    final Point origin;
    final Point destination;
    final GooglePathResponse response;

    private final List<Point> points = new ArrayList<>();

    public DirectPolyPath build() {
        points.add(origin);
        // assuming route exists, should select different routes?
        GooglePathResponse.Route route = response.getRoutes().get(0);
        for (GooglePathResponse.Leg leg : route.getLegs()) {
            for (GooglePathResponse.Step step : leg.getSteps()) {
                points.addAll(new PolyLine(step.getPolyline().getPoints()).getPoints());
            }
        }
        points.add(destination);
        return this;
    }

    public Path getPath() {
        return new Path(points);
    }

}
