package com.ds.rios.deliveryservice.resourse;

import com.ds.rios.deliveryservice.dto.DriverModelAssembler;
import com.ds.rios.deliveryservice.dto.OderModelAssembler;
import com.ds.rios.deliveryservice.model.AssignOrder;
import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.service.DriverService;
import com.ds.rios.deliveryservice.service.OrderService;
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
@RequestMapping("/dd")
public class OrderController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverModelAssembler driverModelAssembler;

    @Autowired
    private OderModelAssembler oderModelAssembler;

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/Orders", produces = {"application/json"})
    public CollectionModel<EntityModel<AssignOrder>> getAllOrders() {
        List<EntityModel<AssignOrder>> collectDrivers = orderService.getAllOrder().stream()
                .map(oderModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(collectDrivers, linkTo(methodOn(DriverController.class).getAllDrivers()).withSelfRel());
    }

    @GetMapping(value = "/Orders/{orderId}", produces = {"application/json"})
    public ResponseEntity<?> getOrderById(@PathVariable("orderId") long orderId) {
        EntityModel<AssignOrder> orderEntityModel = oderModelAssembler.toModel(orderService.getOrderById(orderId));
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);

    }


    @GetMapping(value = "/drivers/{driverId}", produces = {"application/json"})
    public ResponseEntity<?> getDriverById(@PathVariable("driverId") long driverId) {
        EntityModel<Driver> driverEntityModel = driverModelAssembler.toModel(driverService.getDriverById(driverId));
        return ResponseEntity.created(driverEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(driverEntityModel);

    }

    @PutMapping(value = "/Orders/{orderId}", produces = {"application/json"}, consumes = {"application/json"})
    public AssignOrder updateOrders(@RequestBody AssignOrder assignOrder, @PathVariable("orderId") long orderId) {
        return orderService.updateOrder(orderId, assignOrder);
    }


    @PostMapping(value = "/Orders", produces = {"application/json"}, consumes = {"application/json"})
    public AssignOrder newOrders(@RequestBody AssignOrder assignOrder) {
        return orderService.AddNewOrder(assignOrder);
    }

}
