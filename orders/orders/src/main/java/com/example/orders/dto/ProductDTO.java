package com.example.orders.dto;


import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

	   @JsonProperty("productId")
	    private Long productId;

	    @JsonProperty("productName")
	    private String productName;

	    @JsonProperty("price")
	    private BigDecimal price;

    // Constructors, getters, setters, etc.

    public ProductDTO() {
        // Default constructor
    }

    public ProductDTO(Long productId, String productName, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    // Getters and setters
    
}


