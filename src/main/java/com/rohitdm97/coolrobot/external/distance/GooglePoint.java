package com.rohitdm97.coolrobot.external.distance;

import com.rohitdm97.coolrobot.path.Point;
import lombok.*;

@Data
@NoArgsConstructor
public class GooglePoint {
    private double lat;
    private double lng;

    @Override
    public String toString() {
        return lat + "," + lng;
    }

    public Point toCorePoint() {
        return Point.of(lat, lng);
    }
}
