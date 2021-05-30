package com.ds.rios.retailshopservice.dto;

import java.util.List;

public class WarehouseOrder {

    private Long id;
    private Long retailId;
    private int status;
    private List<OrderItem> itemList;

    public WarehouseOrder() {
    }

    public WarehouseOrder(Long id, Long retailId, int status, List<OrderItem> itemList) {
        this.id = id;
        this.retailId = retailId;
        this.status = status;
        this.itemList = itemList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRetailId() {
        return retailId;
    }

    public void setRetailId(Long retailId) {
        this.retailId = retailId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }
}
