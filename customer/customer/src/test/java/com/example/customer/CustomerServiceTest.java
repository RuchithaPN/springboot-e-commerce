package com.example.customer;

import com.example.customer.entity.Customers;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customers> customersList = Arrays.asList(new Customers(), new Customers());
        when(customerRepository.findAll()).thenReturn(customersList);

        // Act
        List<Customers> result = customerService.getAllCustomers();

        // Assert
        assertEquals(customersList, result);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById() {
        // Arrange
        Customers customer = new Customers();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Customers result = customerService.getCustomerById(1L);

        // Assert
        assertEquals(customer, result);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateCustomer() {
        // Arrange
        Customers customer = new Customers();
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customers result = customerService.createCustomer(customer);

        // Assert
        assertEquals(customer, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Customers existingCustomer = new Customers();
        Customers updatedCustomer = new Customers();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(existingCustomer)).thenReturn(updatedCustomer);

        // Act
        Customers result = customerService.updateCustomer(1L, updatedCustomer);

        // Assert
        assertEquals(updatedCustomer, result);
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(existingCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        doNothing().when(customerRepository).deleteById(1L);

        // Act
        customerService.deleteCustomer(1L);

        // Assert
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
