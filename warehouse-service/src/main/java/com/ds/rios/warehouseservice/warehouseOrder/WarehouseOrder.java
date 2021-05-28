package com.ds.rios.warehouseservice.warehouseOrder;

import javax.persistence.*;
import java.util.List;

@Entity
public class WarehouseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long retailId;
    private int status;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> itemList;

    protected WarehouseOrder(){}

    public WarehouseOrder(Long retailId, int status) {
        this.retailId = retailId;
        this.status = status;
    }

    public WarehouseOrder(Long retailId){
        this.retailId = retailId;
        this.status = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRetailId() {
        return retailId;
    }

    public void setRetailId(Long retailId) {
        this.retailId = retailId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }
}


