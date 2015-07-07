package com.tradedoubler.application.controller;

import com.tradedoubler.application.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.tradedoubler.application.constant.ControllerConstant.GREETING;


/**
 * Created by abdal on 2015-07-07.
 */

/**
 * TODO need to add unit test
 * TODO Add slf4j for logging
 * TODO Customize data format (See openplatform uts)
 * TODO Response code
 * TODO Error handling
 * TODO tokenize
 * TODO add mySQL DB
 */

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = GREETING)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Developer") String name,
                             @RequestParam(value = "greetingText", defaultValue = "Welcome") String greetingText) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name), greetingText);
    }
}
