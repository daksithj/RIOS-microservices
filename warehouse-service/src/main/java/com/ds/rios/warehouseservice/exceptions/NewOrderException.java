package com.ds.rios.warehouseservice.exceptions;

public class NewOrderException extends RuntimeException {

    public NewOrderException() {
        super("Could not add new order");
    }
}
