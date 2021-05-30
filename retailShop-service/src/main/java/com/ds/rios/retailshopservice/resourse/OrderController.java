package com.ds.rios.retailshopservice.resourse;
import com.ds.rios.retailshopservice.dto.*;
import com.ds.rios.retailshopservice.model.RetailShop;
import com.ds.rios.retailshopservice.service.OrderService;
import com.ds.rios.retailshopservice.service.RetailShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WarehouseItemModelAssembler warehouseItemModelAssembler;

    @GetMapping(value = "/pending-orders/{retailShopId}", produces = {"application/json"})
    public ResponseEntity<?> getItemWithPendingDelivery(@PathVariable("retailShopId") long retailShopId){
        List<WarehouseOrder> itemWithPendingDelivery = orderService.getItemWithPendingDelivery(retailShopId);
        return new ResponseEntity(itemWithPendingDelivery, HttpStatus.OK);

    }

    @GetMapping(value = "/items", produces = {"application/json"})
    public CollectionModel<EntityModel<WarehouseItem>>  getAvailableItems(){
        List<EntityModel<WarehouseItem>> items = orderService.getAvailableItems().stream()
                .map(warehouseItemModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(OrderController.class).getAvailableItems()).withSelfRel());
    }


    @PostMapping("/orders")
    public ResponseEntity<?> newOrder(@RequestBody OrderRequest newOrder) {
        WarehouseOrder b = orderService.addNewWarehouseOrder(newOrder);
                    return new ResponseEntity(b,HttpStatus.CREATED);

    }
}
