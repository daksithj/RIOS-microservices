package com.ds.rios.deliveryservice.service;

import com.ds.rios.deliveryservice.model.Vehicle;
import com.ds.rios.deliveryservice.repositery.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    public Vehicle addNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(new Vehicle(vehicle.getVehicleNumber(), vehicle.getBrand()));
    }

    public Vehicle updateVehicle(long id, Vehicle vehicle) {
        Optional<Vehicle> byId = vehicleRepository.findById(id);
        if (byId.isPresent()) {
            Vehicle vehicleUpdated = byId.get();
            vehicleUpdated.setDriver(vehicle.getDriver());
            vehicleUpdated.setVehicleNumber(vehicle.getVehicleNumber());

            return vehicleRepository.save(vehicleUpdated);
        }
        return null;


    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }


}
