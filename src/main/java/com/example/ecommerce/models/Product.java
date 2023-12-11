package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private Long id;
    private String name;
    private String category;
    private Long quantity;
    private BigDecimal price;
    private String image;

    public Product( String name, String category, Long quantity, BigDecimal price, String image) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.image = image;
    }


}
