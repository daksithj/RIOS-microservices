package com.ds.rios.warehouseservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NewOrderAdvice {
    @ResponseBody
    @ExceptionHandler(NewOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String NewOrderHandler(NewOrderException ex) {
        return ex.getMessage();
    }
}

