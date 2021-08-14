package com.rohitdm97.coolrobot;

import lombok.extern.log4j.Log4j2;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import com.rohitdm97.coolrobot.path.Point;
import com.rohitdm97.coolrobot.external.distance.*;


import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@Log4j2
@SpringBootApplication
@AllArgsConstructor
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    private PathFinderService pathFinderService;
    private PlaceFinderService placeFinderService;

    @Override
    public void run(String... args) {
        placeFinderService.convert(Point.of(12.93175, 77.62872));
        placeFinderService.convert(Point.of(12.92662, 77.63696));
 //       pathFinderService.findPath();
    }
}
