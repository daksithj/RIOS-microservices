package com.ds.rios.deliveryservice.service;

import com.ds.rios.deliveryservice.dto.DeliveryDetails;
import com.ds.rios.deliveryservice.dto.DriverNotFoundException;

import com.ds.rios.deliveryservice.dto.NotFoundException;
import com.ds.rios.deliveryservice.dto.OrderNotFoundException;
import com.ds.rios.deliveryservice.model.AssignOrder;
import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.model.Vehicle;
import com.ds.rios.deliveryservice.repositery.DriverRepository;
import com.ds.rios.deliveryservice.repositery.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class DriverService {


    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private VehicleService vehicleService;

    private static final String DRIVER_ACTIVE_STATUS = "Available";
    private static final String DRIVER_NOT__ACTIVE_STATUS = "NotAvailable";
    private static final String ORDER_DELIVERY_STATUS_PENDING = "Pending";
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Driver getDriverById(long id) {
        return driverRepository.findById(id).orElseThrow(() -> new DriverNotFoundException(id));
    }

    public Driver addNewDriver(Driver driver) {
        Vehicle vehicle = vehicleService.addNewVehicle(driver.getVehicle());
        Driver newDriver = new Driver(driver.getName(), driver.getIdNumber(), driver.getContactNumber(),driver.getDriverStatus());
        newDriver.setVehicle(vehicle);
        return driverRepository.save(newDriver);
    }

    public DeliveryDetails getDriverItems(long driverId){
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new OrderNotFoundException(driverId));
        List<AssignOrder> assignOrderUpdated = orderRepository.findByAssignmentStatusAndDriverId(ORDER_DELIVERY_STATUS_PENDING,driver.getId());
        if(assignOrderUpdated.size()==0){
            throw  new NotFoundException(driver.getId());
        }
        return orderService.getItemWithPendingDelivery(assignOrderUpdated.get(0).getOrderId());
    }


    public Driver updateDriver(Driver driver, long driverId) {
        Vehicle vehicle = vehicleService.updateVehicle(driver.getVehicle().getId(), driver.getVehicle());
        Optional<Driver> byId = driverRepository.findById(driverId);
        if (byId.isPresent()) {
            Driver driverUpdate = byId.get();
            driverUpdate.setName(driver.getName());
            driverUpdate.setContactNumber(driver.getContactNumber());
            driverUpdate.setIdNumber(driver.getIdNumber());
            driverUpdate.setVehicle(vehicle);
            driverUpdate.setDriverStatus(driver.getDriverStatus());
        }
        return driverRepository.save(driver);
    }


    public Driver getAvailableDrivers(){
        List<Driver> byDriverStatus = driverRepository.findByDriverStatus(DRIVER_ACTIVE_STATUS);
        if(byDriverStatus.size()==0){
            throw  new NotFoundException(0l);
        }
        return byDriverStatus.get(0);
    }

    public Driver updateDriverStatus(long driverId, String newStatus){
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException(driverId));
        driver.setDriverStatus(newStatus);
        return driverRepository.save(driver);
    }

    public void deleteDriverById(long id) {
        driverRepository.deleteById(id);
    }
}
