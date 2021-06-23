package com.ds.rios.retailshopservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class OrderEmbedded {
    @JsonProperty("warehouseOrderList")
    List<WarehouseOrder> warehouseOrders;

    public OrderEmbedded(){

    }

    public OrderEmbedded(List<WarehouseOrder> warehouseOrders) {
        this.warehouseOrders = warehouseOrders;
    }

    public List<WarehouseOrder> getWarehouseOrders() {
        return warehouseOrders;
    }

    public void setWarehouseOrders(List<WarehouseOrder> warehouseOrders) {
        this.warehouseOrders = warehouseOrders;
    }
}