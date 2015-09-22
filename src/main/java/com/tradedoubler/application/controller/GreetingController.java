package com.tradedoubler.application.controller;

import com.tradedoubler.application.model.Greeting;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import static com.tradedoubler.application.constant.ControllerConstant.GREETING;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by abdal on 2015-07-07.
 */

/**
 * TODO need to add unit test
 * TODO add mySQL DB. Use spring in memory database
 * TODO Add more rest interfaces
 * TODO Customize data format (See openplatform uts)
 * TODO Response code
 * TODO Use websocket
 * TODO Error handling
 * TODO tokenize. Configure token in properties file
 */

@RestController
public class GreetingController {

    private static final Logger LOGGER = getLogger(GreetingController.class);
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = GREETING, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Developer") String name,
                             @RequestParam(value = "greetingText", defaultValue = "Welcome") String greetingText) {
        LOGGER.info("greeting controller executed...");
        return new Greeting(counter.incrementAndGet(),
                String.format(TEMPLATE, name), greetingText);
    }
}
