package com.example.ecommerce.services;

import java.sql.SQLException;

@FunctionalInterface
public interface UserService {
    void compile() throws ClassNotFoundException, SQLException;
}
