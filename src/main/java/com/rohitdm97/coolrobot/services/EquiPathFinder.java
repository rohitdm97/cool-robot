package com.rohitdm97.coolrobot.services;

import com.rohitdm97.coolrobot.external.distance.PathFinderService;
import com.rohitdm97.coolrobot.path.Point;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EquiPathFinder {

    private final int stepSize = 50;
    private final PathFinderService pathFinderService;

    public void findPath(Point origin, Point destination) {

    }

}
