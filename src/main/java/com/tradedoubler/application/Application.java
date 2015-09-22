package com.tradedoubler.application;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by abdal on 2015-07-07.
 */

@SpringBootApplication
@EnableSwagger
public class Application {
    private static final Logger LOGGER = getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        LOGGER.info("Application started...");
    }
}
