package com.rohitdm97.coolrobot.external.distance;

import lombok.AllArgsConstructor;
import lombok.With;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rohitdm97.coolrobot.path.*;

import java.util.Map;

@Log4j2
@Service
public class PlaceFinderService {
    @Value("${environment.API_KEY}")
    private String API_KEY;

    private final String base = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";

    @Autowired
    private RestTemplate template;

    public GooglePlace convert(Point p) {
        StringBuilder sb = new StringBuilder(base);
        sb.append("location=").append(p.getLat()).append(',').append(p.getLng());
        sb.append("radius=1500&key=").append(API_KEY);
        ResponseEntity<String> response = template.exchange(base, HttpMethod.GET, new HttpEntity<String>((String) null), String.class);
        String answer =         response.getBody();
        log.info("String is ", answer);
        return null;
    }

}
