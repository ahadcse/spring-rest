package com.tradedoubler.application.controller;

import com.tradedoubler.application.ApplicationUserDetailManager;
import com.tradedoubler.application.error.ErrorCode;
import com.tradedoubler.application.model.GreetingResponse;
import com.tradedoubler.application.model.Registration;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static com.tradedoubler.application.constant.ControllerConstant.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by abdal on 2015-07-07.
 */

/**
 * TODO Encrypt password and use a role for password
 * TODO Delete a user, find a user by username
 * TODO Login
 * TODO Add Rest documentation
 * TODO Collect log in a file
 * TODO need to add unit test
 * TODO Use BindingResult. It is used for binding java class into json
 * TODO Customize data format (See openplatform uts)
 * TODO Response code
 * TODO Use, spring-boot: console, acuator(for endpoint)
 * TODO Use websocket to send data to frontend and javascript, jsp use to show
 * TODO Error handling
 * TODO tokenize. Configure token in properties file. use @ApiResponse(code = 401, message = "Provided token not authorized"),
 */

@RestController
@Api(value = "Application Controller Service", description = "APIs for User Management")
public class ApplicationController {

    private static final Logger LOGGER = getLogger(ApplicationController.class);
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ApplicationUserDetailManager applicationUserDetailManager;

    @ApiOperation(value = "Greets User or Developer", notes = "For GreetingResponse user. Default user: Developer, Default message: Welcome", response = GreetingResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "API successfully executed"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "System error"),
            @ApiResponse(code = 503, message = "Could not connect to external system or unknown error")
    })
    @RequestMapping(value = {GREETING}, method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<GreetingResponse> greeting(@RequestParam(value = "name", defaultValue = "Developer") String name,
                                             @RequestParam(value = "greetingText", defaultValue = "Welcome") String greetingText) {
        LOGGER.info("greetingResponse controller started...");

        GreetingResponse greetingResponse = new GreetingResponse(counter.incrementAndGet(),
                String.format(TEMPLATE, name), greetingText);

        // The response contains an error code indicating a failed attempt
        if(greetingResponse.getErrorCode() != null) {
            LOGGER.warn("Something's messed up!!!");
            switch (greetingResponse.getErrorCode()){
                case UNKNOWN_ERROR:
                    return new ResponseEntity<>(greetingResponse, HttpStatus.SERVICE_UNAVAILABLE);
                default:
                    return new ResponseEntity<>(greetingResponse, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(greetingResponse, HttpStatus.OK);
        }
    }

    @RequestMapping(value = {REGISTER}, method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String register(@RequestParam(value = "user") String user,
                           @RequestParam(value = "password") String password) {
        JSONObject jsonObject = new JSONObject();
        LOGGER.info("register controller executed...");
        Registration registration = new Registration();
        registration.setUsername(user);
        registration.setPassword(password);
        boolean isUserExists = applicationUserDetailManager.userExists(user);
        if (isUserExists) {
            jsonObject.append("User Already Exists.", user);
            return jsonObject.toString();
        }
        applicationUserDetailManager.createUser(registration);
        jsonObject.append("Registered user: ", user);
        LOGGER.info("The user: " + registration.getUsername() + " is registered");
        return jsonObject.toString();
    }

    @RequestMapping(value = {LIST_USER}, method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String[] listUsersWithRole() {
        LOGGER.info("listUsersWithRole controller executed...");
        // Implement if no users what should be shown
        return applicationUserDetailManager.listUsersWithRole(ApplicationUserDetailManager.USER_ROLE);
    }

    @ApiOperation(value = "Get available error codes")
    @RequestMapping(value = {ERROR_CODE}, method = RequestMethod.GET, produces = "application/json")
    public ErrorCode[] errorcodes() {
        return ErrorCode.values();
    }
}
