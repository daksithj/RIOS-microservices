package com.ds.rios.deliveryservice.service;


import com.ds.rios.deliveryservice.dto.DriverNotFoundException;
import com.ds.rios.deliveryservice.dto.OrderNotFoundException;
import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.model.AssignOrder;
import com.ds.rios.deliveryservice.repositery.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DriverService driverService;
    public AssignOrder getOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }



    public AssignOrder AddNewOrder(AssignOrder assignOrder){

        Driver driver = driverService.getDriverById(2);
        AssignOrder newAssignOrder = new AssignOrder(assignOrder.getOrderId(), assignOrder.getShopId(), assignOrder.getLocation());
        newAssignOrder.setDriver(driver);
        return orderRepository.save(newAssignOrder);
    }
    public AssignOrder updateOrder(long id, AssignOrder assignOrder) {
        Optional<AssignOrder> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            AssignOrder assignOrderUpdated = byId.get();
            assignOrderUpdated.setLocation(assignOrder.getLocation());
            assignOrderUpdated.setDriver(assignOrder.getDriver());
            return orderRepository.save(assignOrderUpdated);
        }
        return null;


    }

    public List<AssignOrder> getAllOrder() {
        return orderRepository.findAll();
    }
}
