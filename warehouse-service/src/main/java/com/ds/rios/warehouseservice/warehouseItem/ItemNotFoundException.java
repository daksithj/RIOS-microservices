package com.ds.rios.warehouseservice.warehouseItem;

public class ItemNotFoundException extends RuntimeException {

    ItemNotFoundException(Long id) {
        super("Could not find item: " + id);
    }
}
