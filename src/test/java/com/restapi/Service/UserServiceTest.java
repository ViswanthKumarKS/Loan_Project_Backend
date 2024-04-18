package com.restapi.Service;

import com.restapi.dto.AuthDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    AuthDto authDto;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testRegister(){
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setUsername("Viswanth");
        AppUser appUser=new AppUser();
        appUser.setUsername("Viswanth");
        appUser.setName("Viswanth");
        appUser.setPassword("12345");
        when(authDto.mapToAppUser(Mockito.any())).thenReturn(appUser);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("12345");
        when(roleRepository.findByName(Role.USER)).thenReturn(new Role());
        when(userRepository.save(appUser)).thenReturn(appUser);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setId(1L);
        authResponse.setUsername("Viswanth");
        authResponse.setName("Viswanth");
        authResponse.setRole("USER");
        when(authDto.mapToAuthResponse(Mockito.any())).thenReturn(authResponse);
        AuthResponse actualResponse = userService.register(registerRequest);
        assertEquals(authResponse,actualResponse);
    }
    @Test
    public void testLogin() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("12345");
        AppUser appUser=new AppUser();
        appUser.setUsername("user");
        appUser.setPassword("12345");
        when(userRepository.findByUsername("user")).thenReturn(Optional.of(appUser));
        when(bCryptPasswordEncoder.matches("12345",appUser.getPassword())).thenReturn(true);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(1L);
        authResponse.setName("user");
        authResponse.setUsername("user");
        Role role = new Role();
        role.setName("USER");
        authResponse.setRole(String.valueOf(role));
        System.out.println(authResponse.getName());
        when(authDto.mapToAuthResponse(Mockito.any())).thenReturn(authResponse);
        assertEquals(authResponse,userService.login(loginRequest));
    }

    @Test
    public void testLogin_InvalidUsername() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("nonexistent_user");
        loginRequest.setPassword("12345");
        when(userRepository.findByUsername("nonexistent_user")).thenReturn(java.util.Optional.empty());
        assertThrows(InvalidUserException.class, () -> userService.login(loginRequest));
    }
    @Test
    public void testLogin_InvalidPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("wrong_password");
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setUsername("user");
        appUser.setPassword("$2a$10$1234567890123456789012");
        Role role = new Role();
        role.setName("User");
        appUser.setRoles(role);
        when(userRepository.findByUsername("user")).thenReturn(java.util.Optional.of(appUser));
        when(bCryptPasswordEncoder.matches("wrong_password", appUser.getPassword())).thenReturn(false);
        assertThrows(InvalidUserException.class, () -> userService.login(loginRequest));
    }




}
