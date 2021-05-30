package com.ds.rios.retailshopservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ItemEmbedded {
    @JsonProperty("warehouseItemList")
    List<WarehouseItem> warehouseItemList = new ArrayList<WarehouseItem>();

    public ItemEmbedded(){

    }
    public List<WarehouseItem> getItems() {
        return warehouseItemList;
    }

    public void setItems(List<WarehouseItem> items) {
        this.warehouseItemList = items;
    }

}
