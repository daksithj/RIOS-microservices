package com.ds.rios.deliveryservice.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class DriverNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(DriverNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String driverNotFoundHandler(DriverNotFoundException ex) {
        return ex.getMessage();
    }
}
