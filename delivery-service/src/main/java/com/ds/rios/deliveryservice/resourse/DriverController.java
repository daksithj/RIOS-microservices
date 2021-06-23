package com.ds.rios.deliveryservice.resourse;


import com.ds.rios.deliveryservice.dto.DriverModelAssembler;
import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin(origins = "http://localhost:9527")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverModelAssembler driverModelAssembler;


    @GetMapping(value = "/drivers", produces = {"application/json"})
    public CollectionModel<EntityModel<Driver>> getAllDrivers() {
        List<EntityModel<Driver>> collectDrivers = driverService.getAllDrivers().stream()
                .map(driverModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(collectDrivers, linkTo(methodOn(DriverController.class).getAllDrivers()).withSelfRel());
    }

    @GetMapping(value = "/drivers/{driverId}", produces = {"application/json"})
    public ResponseEntity<?> getDriverById(@PathVariable("driverId") long driverId) {
        EntityModel<Driver> driverEntityModel = driverModelAssembler.toModel(driverService.getDriverById(driverId));
        return ResponseEntity.created(driverEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(driverEntityModel);

    }

    @PostMapping(value = "/drivers", produces = {"application/json"}, consumes = {"application/json"})
    public Driver newDriver(@RequestBody Driver driver) {
        return driverService.addNewDriver(driver);
    }

    @PutMapping(value = "/drivers/{driverId}", produces = {"application/json"}, consumes = {"application/json"})
    public Driver updateDriver(@RequestBody Driver driver, @PathVariable("driverId") long driverId) {
        return driverService.updateDriver(driver, driverId);
    }


    @PutMapping(value = "/drivers/driverStatus/{driverId}/{DriverStatus}", produces = {"application/json"}, consumes = {"application/json"})
    public Driver updateDriver(@PathVariable("driverId") long driverId,@PathVariable("DriverStatus") String driverStatus) {
        return driverService.updateDriverStatus(driverId,driverStatus);
    }


    @DeleteMapping(value = "/drivers/{driverId}", produces = {"application/json"})
    public ResponseEntity deleteDriver(@PathVariable("driverId") long driverId) {
        driverService.deleteDriverById(driverId);
//        return  ResponseEntity.ok().build();
        return ResponseEntity.ok().build();

    }


}
