package com.ds.rios.warehouseservice.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Could not find object: " + id);
    }
}
