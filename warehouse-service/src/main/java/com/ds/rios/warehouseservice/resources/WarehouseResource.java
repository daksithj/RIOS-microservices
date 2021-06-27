package com.ds.rios.warehouseservice.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.ds.rios.warehouseservice.exceptions.DeleteFailedException;
import com.ds.rios.warehouseservice.exceptions.NewOrderException;
import com.ds.rios.warehouseservice.exceptions.NotFoundException;
import com.ds.rios.warehouseservice.exceptions.OrderUpdateException;
import com.ds.rios.warehouseservice.warehouseItem.WarehouseItem;
import com.ds.rios.warehouseservice.warehouseItem.WarehouseItemModelAssembler;
import com.ds.rios.warehouseservice.warehouseItem.WarehouseItemRepository;
import com.ds.rios.warehouseservice.warehouseOrder.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin(origins = "http://localhost:9527")
public class WarehouseResource {

    private final WarehouseItemRepository itemRepository;

    private final WarehouseItemModelAssembler itemAssembler;

    private final WarehouseOrderRepository orderRepository;

    private final WarehouseOrderModelAssembler orderAssembler;

    private final OrderItemRepository orderItemRepository;

    WarehouseResource(WarehouseItemRepository itemRepository,
                      WarehouseItemModelAssembler assembler,
                      WarehouseOrderRepository orderRepository,
                      WarehouseOrderModelAssembler orderAssembler,
                      OrderItemRepository orderItemRepository) {
        this.itemRepository = itemRepository;
        this.itemAssembler = assembler;
        this.orderRepository = orderRepository;
        this.orderAssembler = orderAssembler;
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping("/items")
    public CollectionModel<EntityModel<WarehouseItem>> getAllItems(){

        List<EntityModel<WarehouseItem>> items = itemRepository.findAll().stream()
                .map(itemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(items, linkTo(methodOn(WarehouseResource.class).getAllItems()).withSelfRel());
    }

    @GetMapping("/items/{itemId}")
    public EntityModel<WarehouseItem> getItem(@PathVariable("itemId") long itemId){
        WarehouseItem foundItem = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException(itemId));

        return itemAssembler.toModel(foundItem);
    }

    @PostMapping("/items")
    public ResponseEntity<?> newItem(@RequestBody WarehouseItem newItem) {

        EntityModel<WarehouseItem> entityModel = itemAssembler.toModel(itemRepository.save(newItem));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<?>  updateItem(@RequestBody WarehouseItem newItem, @PathVariable("itemId") long itemId){
        WarehouseItem updatedItem =  itemRepository.findById(itemId)
                .map(item -> {
                    item.setItemName(newItem.getItemName());
                    item.setPrice(newItem.getPrice());
                    item.setQuantity(newItem.getQuantity());
                    return itemRepository.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(itemId);
                    return itemRepository.save(newItem);
                });

        EntityModel<WarehouseItem> entityModel = itemAssembler.toModel(updatedItem);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) {
        try {
            itemRepository.deleteById(itemId);
        } catch (EmptyResultDataAccessException e) {
            throw new DeleteFailedException(itemId);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders")
    public CollectionModel<EntityModel<WarehouseOrder>> getAllOrders(){

        List<EntityModel<WarehouseOrder>> orders = orderRepository.findAll().stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(WarehouseResource.class).getAllOrders()).withSelfRel());
    }

    @GetMapping("/pending-orders")
    public CollectionModel<EntityModel<WarehouseOrder>> getPendingOrders(){

        List<EntityModel<WarehouseOrder>> orders = orderRepository.findByStatus(0).stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(WarehouseResource.class).getAllOrders()).withSelfRel());
    }

    @GetMapping("/orders/{orderId}")
    public EntityModel<WarehouseOrder> getOrder(@PathVariable("orderId") long orderId){
        WarehouseOrder foundOrder = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(orderId));
        return orderAssembler.toModel(foundOrder);
    }

    @GetMapping("/orders/retailShop/{shopId}")
    public CollectionModel<EntityModel<WarehouseOrder>>  getOrderByRetailShopId(@PathVariable("shopId") long shopId){
        List<EntityModel<WarehouseOrder>> orders = orderRepository.findByRetailId(shopId).stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(orders, linkTo(methodOn(WarehouseResource.class).getAllOrders()).withSelfRel());
    }

    @GetMapping("/pending-orders/{retailId}")
    public CollectionModel<EntityModel<WarehouseOrder>> getRetailOrder(@PathVariable("retailId") long retailId) {
        List<EntityModel<WarehouseOrder>> orders = orderRepository.findByRetailIdAndStatus(retailId, 0).stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(WarehouseResource.class).getAllOrders()).withSelfRel());
    }

    @PostMapping("/orders")
    public ResponseEntity<?> newOrder(@RequestBody OrderRequest newOrder) {

        WarehouseOrder order = new WarehouseOrder(newOrder.getRetailId());

        List<OrderItem> orderItemList = new ArrayList<>();

        if (newOrder.getItems().isEmpty()) {
            throw new NewOrderException();
        }

        for(OrderRequest.Item orderItem: newOrder.getItems()){
            WarehouseItem foundItem = itemRepository.findById(orderItem.getItemId()).orElseThrow(()
                    -> new NotFoundException(orderItem.getItemId()));
            if (foundItem.getQuantity() < orderItem.getQuantity()) {
                throw new NewOrderException();
            }
        }

        for(OrderRequest.Item orderItem: newOrder.getItems()){
            WarehouseItem foundItem = itemRepository.findById(orderItem.getItemId()).orElseThrow(()
                    -> new NotFoundException(orderItem.getItemId()));
            OrderItem newOrderItem = new OrderItem(order, foundItem, orderItem.getQuantity());
            orderItemList.add(newOrderItem);
            foundItem.setQuantity(foundItem.getQuantity() - orderItem.getQuantity());
            itemRepository.save(foundItem);

        }

        order.setItemList(orderItemList);
        orderRepository.save(order);

        EntityModel<WarehouseOrder> entityModel = orderAssembler.toModel(orderRepository.save(order));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }


    @PutMapping("/orders/{orderId}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderRequest newOrder, @PathVariable("orderId") long orderId) {

        WarehouseOrder order = orderRepository.findById(orderId).orElseThrow(()
                -> new NotFoundException(orderId));

        if (order.getStatus() != 0) {
            throw new OrderUpdateException(orderId);
        }

        int orderResidual = 0;

        for(OrderRequest.Item orderItem: newOrder.getItems()){
            WarehouseItem foundItem = itemRepository.findById(orderItem.getItemId()).orElseThrow(()
                    -> new NotFoundException(orderItem.getItemId()));
            List<OrderItem> orderItems = orderItemRepository.findByOrderAndWarehouseItem(order, foundItem);

            for (OrderItem item: orderItems) {
                int residual = item.getOrderedAmount() - orderItem.getQuantity();

                if (residual >= 0){
                    orderResidual += residual;
                } else {
                    throw new OrderUpdateException(orderId);
                }
            }

            for (OrderItem item: orderItems) {
                int residual = item.getOrderedAmount() - orderItem.getQuantity();
                item.setOrderedAmount(residual);
                orderItemRepository.save(item);
            }
        }
        if (orderResidual == 0){
            order.setStatus(1);
            orderRepository.save(order);
        }

        EntityModel<WarehouseOrder> entityModel = orderAssembler.toModel(order);

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            WarehouseOrder order = orderRepository.findById(orderId).orElseThrow(()
                    -> new NotFoundException(orderId));

            for (OrderItem orderItem: order.getItemList()) {
                WarehouseItem item = orderItem.getWarehouseItem();
                item.setQuantity(item.getQuantity() + orderItem.getOrderedAmount());
                itemRepository.save(item);
            }
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
            throw new DeleteFailedException(orderId);
        }
        return ResponseEntity.noContent().build();
    }
}
