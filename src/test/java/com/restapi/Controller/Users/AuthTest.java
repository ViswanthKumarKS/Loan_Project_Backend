package com.restapi.Controller.Users;

import com.restapi.controller.Users.AuthController;
import com.restapi.response.AuthResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AuthController.class)
@WithMockUser(username = "user",password = "user",roles = "USER")
public class AuthTest {
    @Autowired
    MockMvc mockMvc;
    @SpyBean
    APIResponse apiResponse;

    @MockBean
    UserService userService;

    @Test
    public void testLogin() throws Exception {
        AuthResponse authResponse=new AuthResponse();
        authResponse.setId(1L);
        authResponse.setName("Viswanth");
        authResponse.setRole("User");
        authResponse.setUsername("Viswanth");
        when(userService.login(Mockito.any())).thenReturn(authResponse);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"chandu\", \"password\":\"1234\"}").with(csrf());
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(mvcResult.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":{\"id\":1,\"username\":\"Viswanth\",\"name\":\"Viswanth\",\"role\":\"User\"},\"error\":null}",mvcResult.getResponse().getContentAsString());

    }
    @Test
    public void testRegister() throws Exception {
        AuthResponse authResponse=new AuthResponse();
        authResponse.setId(1L);
        authResponse.setName("Viswanth");
        authResponse.setRole("User");
        authResponse.setUsername("Viswanth");
        when(userService.register(Mockito.any())).thenReturn(authResponse);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"Viswanth\",\"password\":\"12345\",\"name\":\"Viswanth\"}").with(csrf());
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":{\"id\":1,\"username\":\"Viswanth\",\"name\":\"Viswanth\",\"role\":\"User\"},\"error\":null}",result.getResponse().getContentAsString());

    }

}
