package com.ds.rios.warehouseservice.warehouseOrder;

import com.ds.rios.warehouseservice.resources.WarehouseResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WarehouseOrderModelAssembler implements RepresentationModelAssembler<WarehouseOrder, EntityModel<WarehouseOrder>> {

    public EntityModel<WarehouseOrder> toModel(WarehouseOrder warehouseOrder){

        return EntityModel.of(warehouseOrder,
                linkTo(methodOn(WarehouseResource.class).getOrder(warehouseOrder.getId())).withSelfRel(),
                linkTo(methodOn(WarehouseResource.class).getAllOrders()).withRel("orders")
                );
    }
}
