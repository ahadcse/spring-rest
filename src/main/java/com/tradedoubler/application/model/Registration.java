package com.tradedoubler.application.model;

import javax.validation.constraints.Pattern;

/**
 * Created by abdal on 2015-09-22.
 */
public class Registration {

    // Username only can contains small letters, numbers and length must be between 3 to 15
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$", message = "Unsupported username.")
    private String username;

    // Password must contains at least one number, one small letter, one capital letter, one special character. Length = 8+
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Unsupported password.")
    private String password;

    public Registration() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
