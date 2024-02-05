package com.example.orders.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orders.entity.Orders;


public interface OrderRepository extends JpaRepository<Orders, Long> {
}


