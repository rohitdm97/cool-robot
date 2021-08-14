package com.rohitdm97.coolrobot.external.distance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rohitdm97.coolrobot.path.Point;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class GooglePathResponse {

    private List<Route> routes;

    @Data
    @NoArgsConstructor
    public static class Route {
        private List<Leg> legs;

    }

    @Data
    @NoArgsConstructor
    public static class Leg {
        private Distance distance;
        @JsonProperty("start_location")
        private Point startLocation;
        @JsonProperty("end_location")
        private Point endLocation;
        private List<Step> steps;
    }

    @Data
    @NoArgsConstructor
    public static class Step {
        private Distance distance;
        @JsonProperty("start_location")
        private Point startLocation;
        @JsonProperty("end_location")
        private Point endLocation;
    }

    @Data
    @NoArgsConstructor
    public static class Distance {
        @JsonProperty("value")
        private int meters;
    }
}
