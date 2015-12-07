package com.largecode.test.iklim.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@Data
@AllArgsConstructor
public class RESTResponse {

    public static RESTResponse generateEmptySuccess() {
        return new RESTResponse();
    }

    public static RESTResponse generateSuccess(Object result) {
        return new RESTResponse(true, result);
    }

    public static RESTResponse generateError(String message) {
        return new RESTResponse(false, message);
    }

    protected RESTResponse() {
        this(true, null, null);
    }

    protected RESTResponse(boolean isSuccess, Object result) {
        this(isSuccess, null, result);
    }

    protected RESTResponse(boolean isSuccess, String errorMessage) {
        this(isSuccess, errorMessage, null);
    }

    private final boolean isSuccess;
    private final String errorMessage;
    private final Object result;
}
