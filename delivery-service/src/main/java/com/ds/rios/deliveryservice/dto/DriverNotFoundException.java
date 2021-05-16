package com.ds.rios.deliveryservice.dto;

public class DriverNotFoundException extends RuntimeException {

    public DriverNotFoundException(long id) {
        super("Could not find driver :" + id);
    }
}
