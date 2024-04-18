package com.restapi.Controller.Admin;

import com.restapi.controller.Admin.AdminAccountDetails;
import com.restapi.controller.Users.AccountController;
import com.restapi.model.Account;
import com.restapi.repository.AccountRepository;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AccountService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(AdminAccountDetails.class)
@WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
public class AccountDetailsTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;
    @MockBean
    AccountService accountService;
    @MockBean
    AccountRepository accountRepository;

    public List<Account> returnAccount(){
        List<Account> accountList=new ArrayList<>();
        Account account=new Account();
        account.setId(1L);
        account.setName("sanjay");
        account.setAddress("vissannapeta");
        account.setState("AndhraPradesh");
        account.setCity("vijayawada");
        account.setAcc_no(23456789L);
        account.setBalance(2000.0);
        accountList.add(account);
        return accountList;
    }

    @Test
    public void testAllAdminAccounts() throws Exception {
        when(accountRepository.findAll()).thenReturn(returnAccount());
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/admin/account/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+ actualTimestamp +"\",\"data\":[{\"id\":1,\"name\":\"sanjay\",\"address\":\"vissannapeta\",\"city\":\"vijayawada\",\"acc_no\":23456789,\"balance\":2000.0,\"state\":\"AndhraPradesh\"}],\"error\":null}",result.getResponse().getContentAsString());
    }

}
