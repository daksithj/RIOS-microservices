package com.ds.rios.deliveryservice.service;

import com.ds.rios.deliveryservice.dto.DriverNotFoundException;

import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.model.Vehicle;
import com.ds.rios.deliveryservice.repositery.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {


    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleService vehicleService;


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

    public Driver updateDriverStatus(long driverId, String newStatus){
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new DriverNotFoundException(driverId));
        driver.setDriverStatus(newStatus);
        return driverRepository.save(driver);
    }

    public void deleteDriverById(long id) {
        driverRepository.deleteById(id);
    }
}
