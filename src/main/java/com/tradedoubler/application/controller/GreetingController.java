package com.tradedoubler.application.controller;

import com.tradedoubler.application.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import static com.tradedoubler.application.constant.ControllerConstant.GREETING;


/**
 * Created by abdal on 2015-07-07.
 */

/**
 * TODO need to add unit test
 * TODO Customize data format (See openplatform uts)
 * TODO Response code
 * TODO Use websocket
 * TODO Error handling
 * TODO tokenize. Configure token in properties file
 * TODO add mySQL DB. Use spring in memeory database
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
