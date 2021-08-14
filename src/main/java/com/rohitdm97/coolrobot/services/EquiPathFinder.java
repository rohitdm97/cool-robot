package com.rohitdm97.coolrobot.services;

import com.rohitdm97.coolrobot.external.distance.GooglePathResponse;
import com.rohitdm97.coolrobot.external.distance.PathFinderService;
import com.rohitdm97.coolrobot.path.Path;
import com.rohitdm97.coolrobot.path.Point;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Log4j2
@Service
@AllArgsConstructor
public class EquiPathFinder {

    private final int stepSize = 50;
    private final PathFinderService pathFinderService;

    public Path findPath(Point origin, Point destination) {
        GooglePathResponse response = pathFinderService.findPath(origin, destination);
        log.info("The Polies are\n" );
        response.getRoutes().stream()
                .map(r -> r.getLegs())
                .flatMap(a -> a.stream())
                .map(a -> a.getSteps())
                .flatMap(a -> a.stream())
                .map(a -> a.getPolyline())
                        .map(a -> a.getPoints()).forEach(p -> log.info(p));
        Path p1 = new EquiPath( origin,  destination, stepSize, response).build().getPath();
//        return new DirectPath(origin, destination, response).build().getPath();
//        return new DirectPolyPath(origin, destination, response).build().getPath();

        log.info("\nThe path is \n{}\n", p1);
        return p1;
    }

}
