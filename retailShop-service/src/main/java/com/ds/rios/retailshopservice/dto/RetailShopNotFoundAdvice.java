package com.ds.rios.retailshopservice.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RetailShopNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(RetailShopNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String retailShopNotFoundHandler(RetailShopNotFoundException ex) {
        return ex.getMessage();
    }
}
