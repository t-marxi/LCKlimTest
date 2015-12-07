package com.largecode.test.iklim.exception;

/**
 * Created by iklimovskiy on 30.11.2015.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String login) {
        super(String.format("User was not found for login: %s", login));
    }

}
