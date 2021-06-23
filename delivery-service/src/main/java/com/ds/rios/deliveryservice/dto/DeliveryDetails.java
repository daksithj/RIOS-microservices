package com.ds.rios.deliveryservice.dto;

public class DeliveryDetails {
    public DeliveryDetails(RetailShop retailShop, WarehouseOrder warehouseOrder) {
        this.retailShop = retailShop;
        this.warehouseOrder = warehouseOrder;
    }

    private RetailShop retailShop;
    private WarehouseOrder warehouseOrder;

    public RetailShop getRetailShop() {
        return retailShop;
    }

    protected DeliveryDetails() {
    }

    public void setRetailShop(RetailShop retailShop) {
        this.retailShop = retailShop;
    }

    public WarehouseOrder getWarehouseOrder() {
        return warehouseOrder;
    }

    public void setWarehouseOrder(WarehouseOrder warehouseOrder) {
        this.warehouseOrder = warehouseOrder;
    }
}
