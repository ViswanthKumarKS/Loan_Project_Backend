package com.restapi.Dto;

import com.restapi.dto.AuthDto;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AuthoDtoTest {

    @InjectMocks
    AuthDto authDto;

    @Test
    public void testMapToAppUser(){
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setName("Viswanth");
        registerRequest.setUsername("Viswanth");
        registerRequest.setPassword("12345");
        AppUser appUser=authDto.mapToAppUser(registerRequest);
        assertEquals(appUser.getName(),"Viswanth");
    }

    @Test
    public void testMapToAuthResponse(){
        AppUser appUser=new AppUser();
        appUser.setId(1L);
        appUser.setName("viswanth");
        appUser.setUsername("viswanth");
        Role role = new Role();
        role.setName("ADMIN");
        appUser.setRoles((role));


        AuthResponse authResponse=authDto.mapToAuthResponse(appUser);
        assertEquals(authResponse.getId(),1L);
        assertEquals(authResponse.getUsername(),"viswanth");

    }

}
