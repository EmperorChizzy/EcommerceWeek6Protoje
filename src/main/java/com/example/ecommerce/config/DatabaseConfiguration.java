package com.example.ecommerce.config;

import lombok.Getter;

@Getter
public class DatabaseConfiguration {
    private final String DB_URL = "jdbc:mysql://localhost:3306/ProductDB";
    private final String DB_USERNAME = "root";
    private final String DB_PASSWORD = "Run2safex.1759";
}
