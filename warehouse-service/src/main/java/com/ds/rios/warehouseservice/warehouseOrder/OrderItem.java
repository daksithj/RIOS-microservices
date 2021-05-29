package com.ds.rios.warehouseservice.warehouseOrder;

import com.ds.rios.warehouseservice.warehouseItem.WarehouseItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private Long orderItemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private WarehouseOrder order;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    private WarehouseItem warehouseItem;

    private int orderedAmount;

    protected OrderItem(){}

    public OrderItem(WarehouseOrder order, WarehouseItem warehouseItem, int orderedAmount) {
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
