package com.ds.rios.deliveryservice.dto;

public class VehicleNotFoundException extends RuntimeException {

    public VehicleNotFoundException(long id) {
        super("Could not find vehicle :" + id);
    }
}
