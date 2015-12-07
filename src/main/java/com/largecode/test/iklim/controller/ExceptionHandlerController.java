package com.largecode.test.iklim.controller;

import com.largecode.test.iklim.controller.response.RESTResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
@Slf4j
public abstract class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public RESTResponse handle(Exception e) {
        log.error("Request was failed because: ", e);
        return RESTResponse.generateError(e.getMessage());
    }

}
