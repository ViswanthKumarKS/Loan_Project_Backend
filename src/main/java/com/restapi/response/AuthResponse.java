package com.restapi.response;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class AuthResponse {
    private Long id;


    private String username;


    private String name;


    private String role;
}

