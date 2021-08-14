package com.rohitdm97.coolrobot;

import com.rohitdm97.coolrobot.external.distance.PathFinderService;
import com.rohitdm97.coolrobot.path.Path;
import com.rohitdm97.coolrobot.path.Point;
import com.rohitdm97.coolrobot.services.EquiPathFinder;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Log4j2
@SpringBootApplication
@AllArgsConstructor
public class SpringBootConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    private final PathFinderService pathFinderService;
    private final EquiPathFinder equiPathFinder;

    @Override
    public void run(String... args) {
        Point origin = Point.of(12.93175, 77.62872);
        Point destination = Point.of(12.92662, 77.63696);
        Path path = equiPathFinder.findPath(origin, destination);
    }
}
