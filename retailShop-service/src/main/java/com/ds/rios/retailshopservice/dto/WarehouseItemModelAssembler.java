package com.ds.rios.retailshopservice.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.ds.rios.retailshopservice.resourse.OrderController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WarehouseItemModelAssembler implements RepresentationModelAssembler<WarehouseItem, EntityModel<WarehouseItem>> {

    @Override
    public EntityModel<WarehouseItem> toModel(WarehouseItem warehouseItem) {

        return EntityModel.of(warehouseItem,
                linkTo(methodOn(OrderController.class).getAvailableItems()).withRel("items")
        );
    }
}
