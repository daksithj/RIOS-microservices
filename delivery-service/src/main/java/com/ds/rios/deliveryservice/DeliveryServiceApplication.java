package com.ds.rios.deliveryservice;

import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.model.Vehicle;
import com.ds.rios.deliveryservice.repositery.DriverRepository;
import com.ds.rios.deliveryservice.repositery.VehicleRepository;
import com.ds.rios.deliveryservice.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
public class DeliveryServiceApplication {


    @Autowired
    VehicleService vehicleService;


    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DeliveryServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        return (args) -> {

            Vehicle v1 = new Vehicle("ABC-1212", "Toyota");
            Vehicle v2 = new Vehicle("ABC-12asdas12", "Toyota");
            Driver s = new Driver("AMM11", "97888099V", "7790909","Available");
            Driver s2 = new Driver("AMM22", "97888099V", "7790909","Not Available");
            v1.setDriver(s);
            v2.setDriver(s2);
            s.setVehicle(v1);
            s2.setVehicle(v2);
            vehicleRepository.save(v1);
            driverRepository.save(s);
            vehicleRepository.save(v2);
            driverRepository.save(s2);

        };
    }

}


