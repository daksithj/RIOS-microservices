package com.ds.rios.warehouseservice.warehouseItem;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ds.rios.warehouseservice.resources.WarehouseResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class WarehouseItemModelAssembler implements RepresentationModelAssembler<WarehouseItem, EntityModel<WarehouseItem>> {

    @Override
    public EntityModel<WarehouseItem> toModel(WarehouseItem warehouseItem) {

        return EntityModel.of(warehouseItem,
                linkTo(methodOn(WarehouseResource.class).getItem(warehouseItem.getId())).withSelfRel(),
                linkTo(methodOn(WarehouseResource.class).getAllItems()).withRel("items")
        );
    }
}
