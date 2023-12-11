package com.example.ecommerce.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class Order {
    private Long id;
    private String productIds;
    private BigDecimal totalPrice;

    public void setProductQuantityMap(Map<Product, Integer> productQuantityMap) {
    }
}
