package com.example.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.entity.Customers;
import com.example.customer.service.CustomerService;

//CustomerController.java
@RestController
@RequestMapping("/customers")
public class CustomerController {
 @Autowired
 private CustomerService customerService;

 @GetMapping
 public List<Customers> getAllCustomers() {
     return customerService.getAllCustomers();
 }

 @GetMapping("/{customerId}")
 public Customers getCustomerById(@PathVariable Long customerId) {
     return customerService.getCustomerById(customerId);
 }

 @PostMapping
 public Customers createCustomer(@RequestBody Customers customer) {
     return customerService.createCustomer(customer);
 }

 @PutMapping("/{customerId}")
 public Customers updateCustomer(@PathVariable Long customerId, @RequestBody Customers updatedCustomer) {
     return customerService.updateCustomer(customerId, updatedCustomer);
 }

 @DeleteMapping("/{customerId}")
 public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
     customerService.deleteCustomer(customerId);
     return ResponseEntity.noContent().build();
 }
}


