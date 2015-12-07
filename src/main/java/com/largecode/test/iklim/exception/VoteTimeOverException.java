package com.largecode.test.iklim.exception;

/**
 * Created by iklimovskiy on 30.11.2015.
 */
public class VoteTimeOverException extends Exception {

    public VoteTimeOverException() {
        super("You could not vote for login because time is over. Need do it before 11 a.m.");
    }

}
