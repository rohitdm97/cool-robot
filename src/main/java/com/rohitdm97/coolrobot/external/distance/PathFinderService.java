package com.rohitdm97.coolrobot.external.distance;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;
import com.rohitdm97.coolrobot.path.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;


@Log4j2
@Service
public class PathFinderService {
    @Value("${API_KEY}")
    private String API_KEY;

    private final String base = "https://maps.googleapis.com/maps/api/directions/json?";

    private RestTemplate template = new RestTemplate();

    public GooglePathResponse findPath(Point origin, Point destination) {
        System.out.println("Key is " + API_KEY);
        StringBuilder url= new StringBuilder(base);
        url.append("origin=").append(origin.getLat()).append(",").append(origin.getLng()).append("&destination=").append(destination.getLat()).append(",").append(destination.getLng()).append("&key=").append(API_KEY);

        ResponseEntity<GooglePathResponse> response = template.exchange(url.toString(), HttpMethod.GET, new HttpEntity<>(new Object()), GooglePathResponse.class);
        return response.getBody();
    }

}
