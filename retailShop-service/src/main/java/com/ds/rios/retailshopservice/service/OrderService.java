package com.ds.rios.retailshopservice.service;

import com.ds.rios.retailshopservice.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String WAREHOUSE_SERVICE_URL = "http://WAREHOUSE-SERVICE";
    private static final String DELIVERY_SERVICE_URL = "http://DELIVERY-SERVICE";

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

    public  List<OrderDetailsResponse> getAllOrdersByRetailId(long  retailShopId){
        OrderResponse orderResponses = restTemplate.getForObject(WAREHOUSE_SERVICE_URL+"/warehouse/orders/retailShop/"+retailShopId, OrderResponse.class);
        System.out.println("---------------");
        System.out.println(orderResponses.getOrderEmbedded().getWarehouseOrders().size());
//        return orderResponses;//itemResponse.getEmbedded().getItems();
        return getTheDeliveryDetails(orderResponses);
    };

    public List<OrderDetailsResponse> getTheDeliveryDetails(OrderResponse orderResponses){
        List<OrderDetailsResponse> orderDetailsResponses  = new ArrayList<>();
        for (WarehouseOrder warehouseOrder:
              orderResponses.getOrderEmbedded().getWarehouseOrders()) {
            OrderDetailsResponse orderDetailsResponse= new OrderDetailsResponse();
            orderDetailsResponse.setWarehouseOrder(warehouseOrder);
            orderDetailsResponse.setStatues(0);
            if (warehouseOrder.getStatus()!=0){
                AssignOrder assignOrder = restTemplate.getForObject(DELIVERY_SERVICE_URL+"/delivery/orders/warehouseOrderId/"+warehouseOrder.getId(), AssignOrder.class);
                orderDetailsResponse.setStatues(1);
                orderDetailsResponse.setAssignOrder(assignOrder);
            }

            orderDetailsResponses.add(orderDetailsResponse);
        }

        return orderDetailsResponses;
    }






}
