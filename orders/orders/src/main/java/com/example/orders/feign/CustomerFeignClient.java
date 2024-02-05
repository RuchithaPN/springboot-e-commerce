package com.example.orders.feign;

//CustomerFeignClient.java
import com.example.orders.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CustomerService", url = "http://localhost:8080") // Change URL accordingly
public interface CustomerFeignClient {

 @GetMapping("/customers/{customerId}")
 CustomerDTO getCustomerById(@PathVariable Long customerId);
}

