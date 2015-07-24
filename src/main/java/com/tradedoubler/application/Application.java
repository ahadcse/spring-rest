package com.tradedoubler.application;

import com.mangofactory.swagger.plugin.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by abdal on 2015-07-07.
 */

@SpringBootApplication
@EnableSwagger
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
