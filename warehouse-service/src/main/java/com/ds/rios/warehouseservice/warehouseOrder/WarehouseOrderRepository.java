package com.ds.rios.warehouseservice.warehouseOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseOrderRepository extends JpaRepository<WarehouseOrder, Long> {

    List<WarehouseOrder> findByStatus (int status);

    List<WarehouseOrder> findByRetailIdAndStatus (Long retailId, int status);
}