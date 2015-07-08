package com.tradedoubler.application.controller;

import com.tradedoubler.application.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.tradedoubler.application.constant.ControllerConstant.GREETING;


/**
 * Created by abdal on 2015-07-07.
 */

/**
 * TODO Add configuration
 * TODO Change default port
 * TODO Add properties file
 * TODO need to add unit test
 * TODO Add slf4j for logging  (See openplatform uts)
 * TODO Customize data format (See openplatform uts)
 * TODO Response code
 * TODO Error handling
 * TODO tokenize
 * TODO add mySQL DB
 * TODO Add Swaggar
 */

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // Ensures that HTTP requests to /greeting are mapped to the greeting() method.
    @RequestMapping(value = GREETING, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Developer") String name,
                             @RequestParam(value = "greetingText", defaultValue = "Welcome") String greetingText) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name), greetingText);
    }
}
