package com.rohitdm97.coolrobot.path;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

@Getter
@AllArgsConstructor(staticName = "of")
@With
public class Point {
    private final double lat;
    private final double lng;

    @Override
    public String toString() {
        return lat + "," + lng;
    }
}
