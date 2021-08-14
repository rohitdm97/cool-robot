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
        private Point startLocation;
        private Point endLocation;
        private List<Step> steps;


        @JsonProperty("start_location")
        public void setStartLocation(GooglePoint startLocation) {
            this.startLocation = startLocation.toCorePoint();
        }

        @JsonProperty("end_location")
        public void setEndLocation(GooglePoint endLocation) {
            this.endLocation = endLocation.toCorePoint();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Step {
        private Distance distance;
        private Point startLocation;
        private Point endLocation;
        private PolyLine polyline;

        @JsonProperty("start_location")
        public void setStartLocation(GooglePoint startLocation) {
            this.startLocation = startLocation.toCorePoint();
        }

        @JsonProperty("end_location")
        public void setEndLocation(GooglePoint endLocation) {
            this.endLocation = endLocation.toCorePoint();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Distance {
        @JsonProperty("value")
        private int meters;
    }

    @Data
    @NoArgsConstructor
    public static class PolyLine {
        private String points;
    }
}
