package com.ds.rios.deliveryservice.dto;


public class OrderItem {


    private Long orderItemId;
    private WarehouseOrder order;
    private WarehouseItem warehouseItem;
    private int orderedAmount;

    public OrderItem() {
    }

    public OrderItem(Long orderItemId, WarehouseOrder order, WarehouseItem warehouseItem, int orderedAmount) {
        this.orderItemId = orderItemId;
        this.order = order;
        this.warehouseItem = warehouseItem;
        this.orderedAmount = orderedAmount;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public WarehouseOrder getOrder() {
        return order;
    }

    public void setOrder(WarehouseOrder order) {
        this.order = order;
    }

    public WarehouseItem getWarehouseItem() {
        return warehouseItem;
    }

    public void setWarehouseItem(WarehouseItem warehouseItem) {
        this.warehouseItem = warehouseItem;
    }

    public int getOrderedAmount() {
        return orderedAmount;
    }

    public void setOrderedAmount(int orderedAmount) {
        this.orderedAmount = orderedAmount;
    }
}
