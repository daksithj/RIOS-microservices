package com.ds.rios.retailshopservice.resourse;


import com.ds.rios.retailshopservice.dto.RetialShopModelAssembler;
import com.ds.rios.retailshopservice.model.RetailShop;
import com.ds.rios.retailshopservice.service.RetailShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/retailshop")
public class RetailShopController {

    @Autowired
    private RetailShopService retailShopService;

    @Autowired
    private RetialShopModelAssembler retialShopModelAssembler;









        @GetMapping(value = "/shop/{retailShopId}", produces = {"application/json"})
    public ResponseEntity<?> getRetailShopById(@PathVariable("retailShopId") long retailShopId) {
        EntityModel<RetailShop> retailShopEntityModel = retialShopModelAssembler.toModel(retailShopService.getRetailShopById(retailShopId));
        return ResponseEntity.created(retailShopEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(retailShopEntityModel);

    }

    @GetMapping(value = "/shop", produces = {"application/json"})
    public CollectionModel<EntityModel<RetailShop>> getAllRetailShop() {

        List<EntityModel<RetailShop>> collectRetail = retailShopService.getAllRetailShops().stream()
                .map(retialShopModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(collectRetail, linkTo(methodOn(RetailShopController.class).getAllRetailShop()).withSelfRel());
    }
}
