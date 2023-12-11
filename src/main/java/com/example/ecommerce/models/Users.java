package com.example.ecommerce.models;

import at.favre.lib.crypto.bcrypt.BCrypt;

import com.example.ecommerce.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private BigDecimal balance;


    public Users(UserDto signedUpUser) {
        this.firstname = signedUpUser.getFirstname();
        this.lastname = signedUpUser.getLastname();
        this.username = signedUpUser.getUsername();
        this.email = signedUpUser.getEmail();
        this.password = BCrypt.withDefaults().hashToString(12, signedUpUser.getPassword().toCharArray());

    }
}
