package com.example.ecommerce.dao;


import com.example.ecommerce.config.DatabaseConfiguration;
import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.models.Users;
import com.example.ecommerce.services.UserService;

import java.sql.*;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Logger;

public class AdminDao {


        private Logger logger = Logger.getGlobal();
        private Connection connection;

        public UserService connect = () -> {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DatabaseConfiguration configuration = new DatabaseConfiguration();
            Properties properties = new Properties();
            properties.setProperty("User", configuration.getDB_URL());
            properties.setProperty("Password", configuration.getDB_PASSWORD());
            if (connection==null|| connection.isClosed()){
                connection = DriverManager.getConnection(configuration.getDB_URL(), configuration.getDB_USERNAME(), configuration.getDB_PASSWORD());
            }
        };


        public Function<Users, Boolean> saveAdmin = (admin -> {
            try {
                connect.compile();
            } catch (SQLException | ClassNotFoundException e) {
                logger.warning("SQL exception"+e.getMessage());
                throw new RuntimeException(e);
            }
            String query = "INSERT INTO ProductDB.admin  (username, email, password) VALUES (?, ? ,?) ";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, admin.getUsername());
                preparedStatement.setString(2, admin.getEmail().toLowerCase());
                preparedStatement.setString(3, admin.getPassword());
                return preparedStatement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        public Function<LoginRequestDto, Users> findAdmin = (admin -> {
            try {
                connect.compile();
            } catch (SQLException | ClassNotFoundException e) {
                logger.warning("SQL exception"+e.getMessage());
                throw new RuntimeException(e);
            }
            String query = "SELECT * FROM ProductDB.admin WHERE email = ?";

            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, admin.getEmail().toLowerCase());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    return Users.builder()
                            .id(resultSet.getLong("id"))
//                            .firstname(resultSet.getString("firstname"))
//                            .lastname(resultSet.getString("lastname"))
                            .username(resultSet.getString("username"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password")).build();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return null;
        });


    }


