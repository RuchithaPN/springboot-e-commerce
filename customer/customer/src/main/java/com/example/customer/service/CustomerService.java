package com.example.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.entity.Customers;
import com.example.customer.repository.CustomerRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerService {
 @Autowired
 private CustomerRepository customerRepository;

 public List<Customers> getAllCustomers() {
     return customerRepository.findAll();
 }

 public Customers getCustomerById(Long customerId) {
     return customerRepository.findById(customerId)
             .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + customerId));
 }

 public Customers createCustomer(Customers customer) {
     return customerRepository.save(customer);
 }

 public Customers updateCustomer(Long customerId, Customers updatedCustomer) {
     Customers existingCustomer = getCustomerById(customerId);
     existingCustomer.setFirstName(updatedCustomer.getFirstName());
     existingCustomer.setLastName(updatedCustomer.getLastName());
     existingCustomer.setEmail(updatedCustomer.getEmail());
     existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
     existingCustomer.setAddress(updatedCustomer.getAddress());
     return customerRepository.save(existingCustomer);
 }

 public void deleteCustomer(Long customerId) {
     customerRepository.deleteById(customerId);
 }
}

