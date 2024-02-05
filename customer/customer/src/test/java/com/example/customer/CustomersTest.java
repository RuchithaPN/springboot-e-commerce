package com.example.customer;

import com.example.customer.entity.Customers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomersTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Customers customer = new Customers();

        // Act
        customer.setCustomerID(11);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setAddress("123 Main St");

        // Assert
        assertEquals(11, customer.getCustomerId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("123 Main St", customer.getAddress());
    }
}
