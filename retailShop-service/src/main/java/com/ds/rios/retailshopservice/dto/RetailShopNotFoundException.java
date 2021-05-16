package com.ds.rios.retailshopservice.dto;

public class RetailShopNotFoundException extends RuntimeException {

    public RetailShopNotFoundException(long id) {
        super("Could not find retial shop :" + id);
    }
}