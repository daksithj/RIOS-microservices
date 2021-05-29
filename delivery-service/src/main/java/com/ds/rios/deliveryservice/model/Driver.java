package com.ds.rios.deliveryservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "assignOrders"})
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


    @OneToMany(mappedBy = "driver")
    private Set<AssignOrder> assignOrders;

    protected Driver() {

    }

    public Driver(String name, String idNumber, String contactNumber, String driverStatus) {
        this.name = name;
        this.idNumber = idNumber;
        this.contactNumber = contactNumber;
        this.driverStatus = driverStatus;
    }

    public Set<AssignOrder> getAssignOrders() {
        return assignOrders;
    }

    public void setAssignOrders(Set<AssignOrder> assignOrders) {
        this.assignOrders = assignOrders;
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

}
