package com.ds.rios.warehouseservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderUpdateAdvice {
    @ResponseBody
    @ExceptionHandler(OrderUpdateException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    String DeleteFailedHandler(OrderUpdateException ex) {
        return ex.getMessage();
    }
}
