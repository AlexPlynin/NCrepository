package com.myproject.repository;

import com.myproject.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

    void deleteOrderById(Long id);
}