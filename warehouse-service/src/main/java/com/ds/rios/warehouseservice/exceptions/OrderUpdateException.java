package com.ds.rios.warehouseservice.exceptions;

public class OrderUpdateException extends RuntimeException {

    public OrderUpdateException(Long id) {
        super("Could not update order: " + id);
    }
}
