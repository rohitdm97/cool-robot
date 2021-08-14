package com.rohitdm97.coolrobot.external.distance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rohitdm97.coolrobot.path.Point;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GooglePathResponse {

    private List<Route> routes;

    @Data
    public class Route {
        private List<Leg> legs;

    }

    @Data
    public class Leg {
        private Distance distance;
        @JsonProperty("start_location")
        private Point startLocation;
        @JsonProperty("end_location")
        private Point endLocation;
        private List<Step> steps;
    }

    @Data
    public class Step {
        private Distance distance;
        @JsonProperty("start_location")
        private Point startLocation;
        @JsonProperty("end_location")
        private Point endLocation;
    }

    @Data
    public class Distance {
        @JsonProperty("value")
        private int meters;
    }
}
