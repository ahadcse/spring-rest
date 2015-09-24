package com.tradedoubler.application.config;

/**
 * Created by abdal on 2015-09-21.
 */

import com.tradedoubler.application.ApplicationUserDetailManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class ApplicationConfiguration {
    private static final Logger LOGGER = getLogger(ApplicationConfiguration.class);

    @Autowired
    private Environment environment; // Have to know about it from openplatform-callback

    @Autowired
    private DataSource dataSource;

    @Bean(initMethod = "init")
    public ApplicationUserDetailManager applicationUserDetailManager(){
        return new ApplicationUserDetailManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
