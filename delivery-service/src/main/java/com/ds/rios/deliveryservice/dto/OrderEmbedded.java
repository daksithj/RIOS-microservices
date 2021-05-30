package com.ds.rios.deliveryservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class OrderEmbedded {
    @JsonProperty("WarehouseOrderList")
    List<WarehouseOrder> warehouseOrders = new ArrayList<WarehouseOrder>();

    public OrderEmbedded(){

    }

    public List<WarehouseOrder> getWarehouseOrders() {
        return warehouseOrders;
    }

    public void setWarehouseOrders(List<WarehouseOrder> warehouseOrders) {
        this.warehouseOrders = warehouseOrders;
    }
}