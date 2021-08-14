package com.rohitdm97.coolrobot.external.distance;

import lombok.extern.log4j.Log4j2;
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

    private final String base = "https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=YOUR_API_KEY";

    @Autowired
    private RestTemplate template;

    public void findPath(Point origin, Point destination) {
    //    template.exchange(fooResourceUrl, HttpMethod.POST, request, Foo.class);
    }

}
