package com.example.customer.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
}
