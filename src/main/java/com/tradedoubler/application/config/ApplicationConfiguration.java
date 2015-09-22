package com.tradedoubler.application.config;

/**
 * Created by abdal on 2015-09-21.
 */

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class ApplicationConfiguration {
    private static final Logger LOGGER = getLogger(ApplicationConfiguration.class);

    @Autowired
    private Environment environment;

}
