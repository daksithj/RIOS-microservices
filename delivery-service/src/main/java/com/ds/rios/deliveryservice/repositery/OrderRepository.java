package com.ds.rios.deliveryservice.repositery;

import com.ds.rios.deliveryservice.model.AssignOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<AssignOrder, Long> {
}
