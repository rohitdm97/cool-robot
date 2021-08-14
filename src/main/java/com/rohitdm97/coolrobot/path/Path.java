package com.rohitdm97.coolrobot.path;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;

import java.util.List;
import java.util.StringJoiner;

@Getter
@AllArgsConstructor
public class Path {

    @Delegate
    private List<Point> points;

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        points.forEach(p -> sj.add(p.toString()));
        return sj.toString();
    }

}
