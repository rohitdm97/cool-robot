package com.rohitdm97.coolrobot.path;

import io.leonard.Position;
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
        // for https://mobisoftinfotech.com/tools/plot-multiple-points-on-map/
        return lat + "," + lng + ",";
    }

    public static Point from(Position position) {
        return Point.of(position.getLatitude(), position.getLongitude());
    }

}
