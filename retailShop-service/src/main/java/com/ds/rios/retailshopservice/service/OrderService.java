package com.ds.rios.retailshopservice.service;

import com.ds.rios.retailshopservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String WAREHOUSE_SERVICE_URL = "http://WAREHOUSE-SERVICE";

    public List<WarehouseOrder> getItemWithPendingDelivery(Long id){
       OrderResponse orderResponse=restTemplate.getForObject(WAREHOUSE_SERVICE_URL+"/warehouse/pending-orders/" + id, OrderResponse.class);
       return orderResponse.getOrderEmbedded().getWarehouseOrders();
    }

    public List<WarehouseItem> getAvailableItems(){
        ItemResponse itemResponse =restTemplate.getForObject(WAREHOUSE_SERVICE_URL+"/warehouse/items", ItemResponse.class);
        return itemResponse.getEmbedded().getItems();
    }

    public  WarehouseOrder addNewWarehouseOrder(OrderRequest orderRequest){
        HttpEntity<OrderRequest> request = new HttpEntity<>(orderRequest);
        WarehouseOrder warehouseOrders = restTemplate.postForObject(WAREHOUSE_SERVICE_URL+"/warehouse/orders", request,WarehouseOrder.class);
        return warehouseOrders;
    };






}
