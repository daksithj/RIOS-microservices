package com.ds.rios.warehouseservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class DeleteFailedAdvice {
    @ResponseBody
    @ExceptionHandler(DeleteFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String DeleteFailedHandler(DeleteFailedException ex) {
        return ex.getMessage();
    }
}