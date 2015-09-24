package com.tradedoubler.application.controller;

import com.tradedoubler.application.ApplicationUserDetailManager;
import com.tradedoubler.application.model.Greeting;
import com.tradedoubler.application.model.Registration;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import static com.tradedoubler.application.constant.ControllerConstant.*;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by abdal on 2015-07-07.
 */

/**
 * TODO Login
 * TODO Collect log in a file
 * TODO Authentication, encrypt password(Check existing username and password rule)
 * TODO need to add unit test
 * TODO Customize data format (See openplatform uts)
 * TODO Response code
 * TODO Use websocket to send data tp frontend and javascript, jsp use to show
 * TODO Error handling
 * TODO tokenize. Configure token in properties file
 */

@RestController
public class ApplicationController {

    private static final Logger LOGGER = getLogger(ApplicationController.class);
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ApplicationUserDetailManager applicationUserDetailManager;

    @RequestMapping(value = {INDEX,GREETING}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Developer") String name,
                             @RequestParam(value = "greetingText", defaultValue = "Welcome") String greetingText) {
        LOGGER.info("greeting controller executed...");
        return new Greeting(counter.incrementAndGet(),
                String.format(TEMPLATE, name), greetingText);
    }

    @RequestMapping(value = {REGISTER}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Registration register(@RequestParam(value = "user") String user,
                                 @RequestParam(value = "password") String password) {
        LOGGER.info("register controller executed...");
        Registration registration = new Registration(user,password);
        applicationUserDetailManager.createUser(registration);
        LOGGER.info("The user: " + registration.getUsername() + " is registered");
        return registration;
    }

    @RequestMapping(value = {LIST_USER}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String[] listUser(){
        LOGGER.info("listUser controller executed...");
        return  applicationUserDetailManager.listAllUsersWithRole(ApplicationUserDetailManager.USER_ROLE);
    }
}
