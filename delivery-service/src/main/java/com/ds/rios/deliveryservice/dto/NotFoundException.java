package com.ds.rios.deliveryservice.dto;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Could not find object: " + id);
    }
}
