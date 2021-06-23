package com.ds.rios.retailshopservice.dto;

public class OrderDetailsResponse {

    private WarehouseOrder warehouseOrder;
    private int statues;
    private  AssignOrder assignOrder;

    public OrderDetailsResponse() {
    }

    public OrderDetailsResponse(WarehouseOrder warehouseOrder, int statues, AssignOrder assignOrder) {
        this.warehouseOrder = warehouseOrder;
        this.statues = statues;
        this.assignOrder = assignOrder;
    }

    public WarehouseOrder getWarehouseOrder() {
        return warehouseOrder;
    }

    public void setWarehouseOrder(WarehouseOrder warehouseOrder) {
        this.warehouseOrder = warehouseOrder;
    }

    public int getStatues() {
        return statues;
    }

    public void setStatues(int statues) {
        this.statues = statues;
    }

    public AssignOrder getAssignOrder() {
        return assignOrder;
    }

    public void setAssignOrder(AssignOrder assignOrder) {
        this.assignOrder = assignOrder;
    }
}
