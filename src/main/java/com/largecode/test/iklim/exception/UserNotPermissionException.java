package com.largecode.test.iklim.exception;

/**
 * Created by iklimovskiy on 30.11.2015.
 */
public class UserNotPermissionException extends Exception {

    public UserNotPermissionException(String login) {
        super(String.format("User %s doesn't have permission for this request.", login));
    }

}
