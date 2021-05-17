package com.ds.rios.retailshopservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CreaditBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shopOwnerName;
    private float amount;

    protected CreaditBalance(){}

    public CreaditBalance(String shopOwnerName, float amount) {
        this.shopOwnerName = shopOwnerName;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopOwnerName() {
        return shopOwnerName;
    }

    public void setShopOwnerName(String shopOwnerName) {
        this.shopOwnerName = shopOwnerName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
