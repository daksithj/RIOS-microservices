package com.ds.rios.warehouseservice.warehouseItem;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class WarehouseItemModelAssembler implements RepresentationModelAssembler<WarehouseItem, EntityModel<WarehouseItem>> {

    @Override
    public EntityModel<WarehouseItem> toModel(WarehouseItem warehouseItem) {

        return EntityModel.of(warehouseItem,
                linkTo(methodOn(WarehouseItemResource.class).getItem(warehouseItem.getId())).withSelfRel(),
                linkTo(methodOn(WarehouseItemResource.class).getAllItems()).withRel("items")
        );
    }
}
