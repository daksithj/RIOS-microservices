package com.ds.rios.warehouseservice.warehouseItem;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WarehouseItemResource {

    private final WarehouseItemRepository repository;

    private final WarehouseItemModelAssembler assembler;

    WarehouseItemResource(WarehouseItemRepository repository,
                          WarehouseItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/items")
    CollectionModel<EntityModel<WarehouseItem>> getAllItems(){

        List<EntityModel<WarehouseItem>> items = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(WarehouseItemResource.class).getAllItems()).withSelfRel());
    }

    @GetMapping("/items/{itemId}")
    EntityModel<WarehouseItem> getItem(@PathVariable("itemId") long itemId){
        WarehouseItem foundItem = repository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(itemId));

        return assembler.toModel(foundItem);
    }

    @PostMapping("/items")
    ResponseEntity<?> newItem(@RequestBody WarehouseItem newItem) {

        EntityModel<WarehouseItem> entityModel = assembler.toModel(repository.save(newItem));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @PutMapping("/items/{itemId}")
    ResponseEntity<?>  updateItem(@RequestBody WarehouseItem newItem, @PathVariable("itemId") long itemId){
        WarehouseItem updatedItem =  repository.findById(itemId)
                .map(item -> {
                    item.setItemName(newItem.getItemName());
                    item.setPrice(newItem.getPrice());
                    item.setQuantity(newItem.getQuantity());
                    return repository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(itemId);
                    return repository.save(newItem);
                });

        EntityModel<WarehouseItem> entityModel = assembler.toModel(updatedItem);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/items/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        repository.deleteById(itemId);
        return ResponseEntity.noContent().build();
    }
}
