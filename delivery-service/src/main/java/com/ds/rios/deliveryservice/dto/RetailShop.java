package com.ds.rios.deliveryservice.dto;



public class RetailShop {


    private Long id;
    private String shopName;
    private String contactNumber;
    private String address;

    protected RetailShop(){}

    public RetailShop(String shopName, String contactNumber, String address) {
        this.shopName = shopName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
