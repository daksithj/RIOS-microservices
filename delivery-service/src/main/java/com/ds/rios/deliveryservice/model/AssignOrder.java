package com.ds.rios.deliveryservice.model;


import javax.persistence.*;


@Entity
public class AssignOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long orderId;
    private long shopId;
    private String assignmentStatus;

    @ManyToOne
    @JoinColumn(name="driver_id", nullable=false)
    private Driver driver;

    protected AssignOrder() {
    }

    public AssignOrder(long orderId, long shopId, String assignmentStatus) {
        this.orderId = orderId;
        this.shopId = shopId;
        this.assignmentStatus = assignmentStatus;
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

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
