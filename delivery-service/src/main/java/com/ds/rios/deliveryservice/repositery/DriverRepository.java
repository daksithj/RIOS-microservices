package com.ds.rios.deliveryservice.repositery;

import com.ds.rios.deliveryservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByDriverStatus(String driverStatus);
}
