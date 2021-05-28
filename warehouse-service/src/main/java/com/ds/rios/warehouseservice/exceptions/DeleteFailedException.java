package com.ds.rios.warehouseservice.exceptions;

public class DeleteFailedException extends RuntimeException {

    public DeleteFailedException(Long id) {
        super("Could not delete object: " + id);
    }

}
