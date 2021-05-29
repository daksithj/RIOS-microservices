package com.ds.rios.deliveryservice.dto;



public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(long id) {
        super("Could not find Order :" + id);
    }
}
