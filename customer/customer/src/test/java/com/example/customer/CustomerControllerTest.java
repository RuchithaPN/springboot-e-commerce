package com.example.customer;

import com.example.customer.controller.CustomerController;
import com.example.customer.entity.Customers;
import com.example.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        List<Customers> customersList = Arrays.asList(new Customers(), new Customers());
        when(customerService.getAllCustomers()).thenReturn(customersList);

        // Act
        List<Customers> result = customerController.getAllCustomers();

        // Assert
        assertEquals(customersList, result);
        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    public void testGetCustomerById() {
        // Arrange
        Customers customer = new Customers();
        when(customerService.getCustomerById(1L)).thenReturn(customer);

        // Act
        Customers result = customerController.getCustomerById(1L);

        // Assert
        assertEquals(customer, result);
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    public void testCreateCustomer() {
        // Arrange
        Customers customer = new Customers();
        when(customerService.createCustomer(customer)).thenReturn(customer);

        // Act
        Customers result = customerController.createCustomer(customer);

        // Assert
        assertEquals(customer, result);
        verify(customerService, times(1)).createCustomer(customer);
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Customers updatedCustomer = new Customers();
        when(customerService.updateCustomer(1L, updatedCustomer)).thenReturn(updatedCustomer);

        // Act
        Customers result = customerController.updateCustomer(1L, updatedCustomer);

        // Assert
        assertEquals(updatedCustomer, result);
        verify(customerService, times(1)).updateCustomer(1L, updatedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();
        doNothing().when(customerService).deleteCustomer(1L);

        // Act
        ResponseEntity<Void> result = customerController.deleteCustomer(1L);

        // Assert
        assertEquals(expectedResponse, result);
        verify(customerService, times(1)).deleteCustomer(1L);
    }
}

