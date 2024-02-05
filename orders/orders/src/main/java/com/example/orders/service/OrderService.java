package com.example.orders.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.orders.dto.CustomerDTO;
import com.example.orders.dto.ProductDTO;
import com.example.orders.entity.OrderItem;
import com.example.orders.entity.Orders;
import com.example.orders.feign.CustomerFeignClient;
import com.example.orders.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate; // Autowire RestTemplate
    private final CustomerFeignClient customerFeignClient;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, CustomerFeignClient customerFeignClient) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.customerFeignClient = customerFeignClient;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public Orders getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public Orders createOrder(Orders order) {
        List<OrderItem> orderItems = order.getOrderItems();
        if (orderItems != null && !orderItems.isEmpty()) {
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(order);

                // Fetch product information from the product service
                ProductDTO productDTO = fetchProductInfo(orderItem.getProductId());
                orderItem.setProductDTO(productDTO);
                // Fetch customer information from the customer microservice
                CustomerDTO customerDTO = customerFeignClient.getCustomerById((long) order.getCustomerId());
                // Set customer details in the order (modify Orders entity to include customer details)
                order.setCustomerDTO(customerDTO);
            }
        }
        return orderRepository.save(order);
    }

    private ProductDTO fetchProductInfo(Long productId) {
    	 String productServiceUrl = "http://localhost:8081/products/" + productId;
    	 
        // Make an HTTP request to the product service
        ResponseEntity<ProductDTO> response = restTemplate.getForEntity(productServiceUrl, ProductDTO.class);

        // Extract the product information from the response
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            // Handle error or return null, depending on your requirements
            return null;
        }
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItem;
    }

}
