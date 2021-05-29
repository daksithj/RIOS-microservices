package com.ds.rios.deliveryservice.dto;

import com.ds.rios.deliveryservice.model.AssignOrder;
import com.ds.rios.deliveryservice.model.Driver;
import com.ds.rios.deliveryservice.resourse.DriverController;
import com.ds.rios.deliveryservice.resourse.OrderController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class OderModelAssembler implements RepresentationModelAssembler<AssignOrder, EntityModel<AssignOrder>> {
    @Override
    public EntityModel<AssignOrder> toModel(AssignOrder assignOrder) {
        return EntityModel.of(assignOrder,
                linkTo(methodOn(OrderController.class).getOrderById(assignOrder.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getAllOrders()).withRel("orders")
        );
    }
}


