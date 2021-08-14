package com.rohitdm97.coolrobot.services;

import com.rohitdm97.coolrobot.external.distance.GooglePathResponse;
import com.rohitdm97.coolrobot.path.Path;
import com.rohitdm97.coolrobot.path.Point;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class DirectPath {
    final Point origin;
    final Point destination;
    final GooglePathResponse response;

    private final List<Point> points = new ArrayList<>();

    public DirectPath build() {
        points.add(origin);
        // assuming route exists, should select different routes?
        GooglePathResponse.Route route = response.getRoutes().get(0);
        for (GooglePathResponse.Leg leg : route.getLegs()) {
            for (GooglePathResponse.Step step : leg.getSteps()) {
                points.add(step.getEndLocation());
            }
        }
        points.add(destination);
        return this;
    }

    public Path getPath() {
        return new Path(points);
    }

}
