package com.ds.rios.deliveryservice.repositery;

import com.ds.rios.deliveryservice.model.AssignOrder;
import com.ds.rios.deliveryservice.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<AssignOrder, Long> {


    List<AssignOrder> findByAssignmentStatusAndDriverId(String assignmentStatus,long driverId);
}
