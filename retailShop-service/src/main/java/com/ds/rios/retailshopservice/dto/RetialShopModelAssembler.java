package com.ds.rios.retailshopservice.dto;

import com.ds.rios.retailshopservice.model.RetailShop;

import com.ds.rios.retailshopservice.resourse.RetailShopController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RetialShopModelAssembler implements RepresentationModelAssembler<RetailShop, EntityModel<RetailShop>> {
    @Override
    public EntityModel<RetailShop> toModel(RetailShop retailShop) {
        return EntityModel.of(retailShop,
                linkTo(methodOn(RetailShopController.class).getRetailShopById(retailShop.getId())).withSelfRel(),
                linkTo(methodOn(RetailShopController.class).getAllRetailShop()).withRel("retial")
        );
    }
}
