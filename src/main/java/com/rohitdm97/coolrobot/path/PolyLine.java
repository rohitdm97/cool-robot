package com.rohitdm97.coolrobot.path;

import io.leonard.PolylineUtils;
import io.leonard.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class PolyLine {

    @Getter
    private final List<Point> points;
    private String raw;

    public PolyLine(String raw) {
        this.raw = raw;

        List<Position> pos = PolylineUtils.decode(raw, 5);
        points = pos.stream().map(Point::from).collect(Collectors.toList());
    }

}
