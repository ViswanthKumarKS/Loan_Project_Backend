package com.restapi.Controller.Users;

import com.restapi.controller.Users.AccountController;
import com.restapi.model.Account;
import com.restapi.repository.AccountRepository;
import com.restapi.request.AccountRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AccountService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(AccountController.class)
@WithMockUser(username = "user",password = "user",roles = "USER")
public class AccountTest {

    @Autowired
    MockMvc mockMvc;
    @SpyBean
    APIResponse apiResponse;
    @MockBean
    AccountService accountService;
    @MockBean
    AccountRepository accountRepository;

    public List<AccountResponse> allAccounts(){
        List<AccountResponse> accountResponseList=new ArrayList<>();
        AccountResponse accountResponse=new AccountResponse();
        accountResponse.setId(3L);
        accountResponse.setName("Sanjay");
        accountResponse.setAddress("12-32,Kondavapara");
        accountResponse.setState("AndhraPradesh");
        accountResponse.setCity("Vijayawada");
        accountResponse.setAcc_no(5104702746780L);
        accountResponse.setBalance(2000.0);
        accountResponse.setUsername("sanjay");
        accountResponseList.add(accountResponse);
        return accountResponseList;
    }


    @Test

    public void testAllAccounts() throws Exception {
        when(accountService.findAll()).thenReturn(allAccounts());

        RequestBuilder req= get("/api/account/all")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult res=mockMvc.perform(req).andExpect(status()
                        .isOk())
                .andExpect(status().isOk())
               .andReturn();
        JSONObject actualJson = new JSONObject(res.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
      System.out.println(res.getResponse().getContentAsString());
        assertEquals("{\"status\":200,\"timestamp\":\"" + actualTimestamp + "\",\"data\":[{\"accountObjects\":[],\"id\":3,\"acc_no\":5104702746780,\"balance\":2000.0,\"name\":\"Sanjay\",\"address\":\"12-32,Kondavapara\",\"state\":\"AndhraPradesh\",\"city\":\"Vijayawada\",\"username\":\"sanjay\"}],\"error\":null}", res.getResponse().getContentAsString());


    }


    @Test
    public void testCreateAccount() throws Exception {
        Account accountResponse=new Account();
        accountResponse.setId(6L);
        accountResponse.setName("Ram");
        accountResponse.setAddress("Chimakurathi");
        accountResponse.setState("AndhraPradesh");
        accountResponse.setCity("Ongole");
        accountResponse.setAcc_no(9866865769L);
        accountResponse.setBalance(987687.0);

        when(accountService.createAccount(Mockito.any())).thenReturn(accountResponse);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/account/create").contentType(MediaType.APPLICATION_JSON).content("{\"id\":6,\"acc_no\":9866865769,\"balance\":987687.0,\"name\":\"Ram\",\"address\":\"Chimakurathi\",\"state\":\"AndhraPradesh\",\"city\":\"Ongole\",\"username\":\"Ram\",\"user_id\":6}").with(csrf());
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":{\"id\":6,\"name\":\"Ram\",\"address\":\"Chimakurathi\",\"city\":\"Ongole\",\"acc_no\":9866865769,\"balance\":987687.0,\"state\":\"AndhraPradesh\"},\"error\":null}",result.getResponse().getContentAsString());
    }
    @Test
    public void testOneAccount() throws Exception {
        AccountResponse accountResponse=new AccountResponse();
        accountResponse.setId(3L);
        accountResponse.setName("sanjay");
        accountResponse.setAddress("12-32,Kondavapara");
        accountResponse.setState("AndhraPradesh");
        accountResponse.setCity("Vijayawada");
        accountResponse.setAcc_no(5104702746780L);
        accountResponse.setBalance(2000.0);
        accountResponse.setUsername("sanjay");
        when(accountService.findById(4L)).thenReturn(accountResponse);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/api/account/detail/4");
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String Timestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\"" + Timestamp + "\",\"data\":{\"accountObjects\":[],\"id\":3,\"acc_no\":5104702746780,\"balance\":2000.0,\"name\":\"sanjay\",\"address\":\"12-32,Kondavapara\",\"state\":\"AndhraPradesh\",\"city\":\"Vijayawada\",\"username\":\"sanjay\"},\"error\":null}", result.getResponse().getContentAsString());
    }
    @Test
    public void testDeleteAccount() throws Exception {
        Integer id=123;
        List<AccountResponse> accounts=new ArrayList<>();
        AccountResponse accountResponse=new AccountResponse();
        accountResponse.setId(3L);
        accountResponse.setName("sanjay");
        accountResponse.setAddress("12-32,Kondavapara");
        accountResponse.setState("AndhraPradesh");
        accountResponse.setCity("Vijayawada");
        accountResponse.setAcc_no(5104702746780L);
        accountResponse.setBalance(2000.0);
        accountResponse.setUsername("sanjay");
        accounts.add(accountResponse);
        when(accountService.deleteById(id)).thenReturn(accounts);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.delete("/api/account/123").contentType(MediaType.APPLICATION_JSON).with(csrf());
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String Timestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+Timestamp+"\",\"data\":[{\"accountObjects\":[],\"id\":3,\"acc_no\":5104702746780,\"balance\":2000.0,\"name\":\"sanjay\",\"address\":\"12-32,Kondavapara\",\"state\":\"AndhraPradesh\",\"city\":\"Vijayawada\",\"username\":\"sanjay\"}],\"error\":null}",result.getResponse().getContentAsString());

    }


}