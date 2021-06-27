package com.ds.rios.deliveryservice.service;


import com.ds.rios.deliveryservice.dto.*;
import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.model.AssignOrder;
import com.ds.rios.deliveryservice.repositery.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;
    private static final String WAREHOUSE_SERVICE_URL = "http://WAREHOUSE-SERVICE";
    private static final String RETAILSHOP_SERVICE_URL = "http://RETAILSHOP-SERVICE";
    private static final String DRIVER_ACTIVE_STATUS = "Available";
    private static final String DRIVER_NOT__ACTIVE_STATUS = "NotAvailable";
    private static final String ORDER_DELIVERY_STATUS_PICKED_UP = "Picked up";
    private static final String ORDER_DELIVERY_STATUS_PENDING = "Pending";
    private static final String ORDER_DELIVERY_STATUS_COMPLETED = "Completed";

    @Autowired
    private DriverService driverService;
    public AssignOrder getOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public AssignOrder getOrderByWareHouseOrderId(long id) {
        return orderRepository.findByOrderId(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    public AssignOrder pickUpOrder(long orderId){
        AssignOrder assignOrderUpdated = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        assignOrderUpdated.setAssignmentStatus(ORDER_DELIVERY_STATUS_PICKED_UP);
        return orderRepository.save(assignOrderUpdated);

    }

    public DeliveryDetails getItemWithPendingDelivery(Long id){
        WarehouseOrder warehouseOrder =restTemplate.getForObject(WAREHOUSE_SERVICE_URL+"/warehouse/orders/" + id, WarehouseOrder.class);
        RetailShop retailShop =restTemplate.getForObject(RETAILSHOP_SERVICE_URL+"/retailShop/shops/" + warehouseOrder.getRetailId(), RetailShop.class);
        return new DeliveryDetails(retailShop,warehouseOrder);
    }


    public AssignOrder completeOrder(long orderId){
        AssignOrder assignOrderUpdated = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        assignOrderUpdated.setAssignmentStatus(ORDER_DELIVERY_STATUS_COMPLETED);
        Driver driver = assignOrderUpdated.getDriver();
        driver.setDriverStatus(DRIVER_ACTIVE_STATUS);
        driverService.updateDriver(driver, driver.getId());
        return orderRepository.save(assignOrderUpdated);
    }

    public AssignOrder cancelOrder(long orderId){
        AssignOrder assignOrderUpdated = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        assignOrderUpdated.setAssignmentStatus(ORDER_DELIVERY_STATUS_PENDING);
        Driver driver = assignOrderUpdated.getDriver();
        Driver availableDrivers = driverService.getAvailableDrivers();
        assignOrderUpdated.setDriver(availableDrivers);
        driverService.updateDriverStatus(availableDrivers.getId(),DRIVER_NOT__ACTIVE_STATUS);
        driver.setDriverStatus(DRIVER_ACTIVE_STATUS);
        driverService.updateDriver(driver, driver.getId());
        return orderRepository.save(assignOrderUpdated);
    }

    public AssignOrder AddNewOrder(AssignOrder assignOrder){

        Driver driver = driverService.getAvailableDrivers();
        AssignOrder newAssignOrder = new AssignOrder(assignOrder.getOrderId(), assignOrder.getShopId(), ORDER_DELIVERY_STATUS_PENDING);
        newAssignOrder.setDriver(driver);
        driverService.updateDriverStatus(driver.getId(),DRIVER_NOT__ACTIVE_STATUS);
        return orderRepository.save(newAssignOrder);
    }
    public AssignOrder updateOrder(long id, AssignOrder assignOrder) {
        Optional<AssignOrder> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            AssignOrder assignOrderUpdated = byId.get();
            assignOrderUpdated.setAssignmentStatus(assignOrder.getAssignmentStatus());
            assignOrderUpdated.setDriver(assignOrder.getDriver());
            return orderRepository.save(assignOrderUpdated);
        }
        return null;


    }

    public List<AssignOrder> getAllOrder() {
        return orderRepository.findAll();
    }
}
