package com.ds.rios.warehouseservice.warehouseItem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WarehouseItem {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String itemName;
    private int quantity;
    private float price;

    protected WarehouseItem() {}

    public WarehouseItem(String itemName, int quantity, float price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
