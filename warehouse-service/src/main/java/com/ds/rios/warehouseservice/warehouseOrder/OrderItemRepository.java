package com.ds.rios.warehouseservice.warehouseOrder;

import com.ds.rios.warehouseservice.warehouseItem.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderAndWarehouseItem(WarehouseOrder order, WarehouseItem warehouseItem);

}