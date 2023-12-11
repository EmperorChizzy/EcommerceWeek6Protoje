package com.example.ecommerce.dao;


import com.example.ecommerce.config.DatabaseConfiguration;
import com.example.ecommerce.dto.LoginRequestDto;
import com.example.ecommerce.models.Users;
import com.example.ecommerce.services.UserService;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Logger;

public class UserDao {
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


    public Function<Users, Boolean> saveUser = (users -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "INSERT INTO ProductDB.users  (firstname, lastname, username, email, password, balance) VALUES (?, ?, ?, ?, ?, ?) ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, users.getFirstname());
            preparedStatement.setString(2, users.getLastname());
            preparedStatement.setString(3, users.getUsername());
            preparedStatement.setString(4, users.getEmail().toLowerCase());
            preparedStatement.setString(5, users.getPassword());
            preparedStatement.setDouble(6, new BigDecimal(50000000).doubleValue());

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });


    public Function<Users, Boolean> updateUserBalance = (user -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "UPDATE ProductDB.users SET balance = ? WHERE id = ? ";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setDouble(2, user.getBalance().doubleValue());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });

    public Function<LoginRequestDto, Users> findUser = (users -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM ProductDB.users WHERE email = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, users.getEmail().toLowerCase());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Users.builder()
                        .id(resultSet.getLong("id"))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .balance(resultSet.getBigDecimal("balance")).build();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    });



    public Function<Long, Users> findUserById = (id -> {
        try {
            connect.compile();
        } catch (SQLException | ClassNotFoundException e) {
            logger.warning("SQL exception"+e.getMessage());
            throw new RuntimeException(e);
        }
        String query = "SELECT * FROM ProductDB.users WHERE id = ?";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Users.builder()
                        .id(resultSet.getLong("id"))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .balance(resultSet.getBigDecimal("balance")).build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    });


}
