package com.ds.rios.deliveryservice.model;


import javax.persistence.*;


@Entity
public class AssignOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long orderId;
    private long shopId;
    private String location;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    protected AssignOrder() {
    }

    public AssignOrder(long orderId, long shopId, String location) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
