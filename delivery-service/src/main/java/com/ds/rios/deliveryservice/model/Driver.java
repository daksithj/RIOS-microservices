package com.ds.rios.deliveryservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "assignOrder"})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String idNumber;
    private String contactNumber;
    private String driverStatus;



    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;


    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "driver")
    private AssignOrder assignOrder;

    protected Driver() {

    }

    public Driver(String name, String idNumber, String contactNumber, String driverStatus) {
        this.name = name;
        this.idNumber = idNumber;
        this.contactNumber = contactNumber;
        this.driverStatus = driverStatus;
    }

    public String getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(String driverStatus) {
        this.driverStatus = driverStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public AssignOrder getAssignOrder() {
        return assignOrder;
    }

    public void setAssignOrder(AssignOrder assignOrder) {
        this.assignOrder = assignOrder;
    }

}
