package com.tradedoubler.application.model;

/**
 * Created by abdal on 2015-09-22.
 */
public class Registration {
    private String username;
    private String password;

    public Registration(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
