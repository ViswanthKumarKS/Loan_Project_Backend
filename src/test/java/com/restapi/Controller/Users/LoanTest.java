package com.restapi.Controller.Users;

import com.restapi.controller.Users.LoanController;
import com.restapi.model.Loan;
import com.restapi.repository.LoanRepository;
import com.restapi.response.LoanResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.LoanService;
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

import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LoanController.class)
@WithMockUser(username = "user",password = "user",roles = "USER")
public class LoanTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    LoanService loanService;

    @MockBean
    LoanRepository loanRepository;

    public List<LoanResponse> returnLoan(){
        List<LoanResponse> loanResponseList=new ArrayList<>();
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setLoanType("carLoan");
        loanResponse.setAmount(2000L);
        loanResponse.setUsername("sanjay");
        loanResponseList.add(loanResponse);
        return loanResponseList;
    }

    @Test
    public void testAllLoans() throws Exception {
        when(loanService.findall()).thenReturn(returnLoan());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/loan/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        System.out.println(result.getResponse().getContentAsString());
        assertEquals("{\"status\":200,\"timestamp\":\"" + actualTimestamp + "\",\"data\":[{\"loans\":[],\"id\":1,\"amount\":2000,\"username\":\"sanjay\",\"approved\":false,\"loanType\":\"carLoan\"}],\"error\":null}", result.getResponse().getContentAsString());
    }

    @Test
    public void testOneLoan() throws Exception{
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setLoanType("carLoan");
        loanResponse.setAmount(2000L);
        loanResponse.setUsername("sanjay");
       when(loanService.findById(3L)).thenReturn(loanResponse);
       RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/api/loan/detail/3");
       MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        System.out.println(result.getResponse().getContentAsString());
        assertEquals("{\"status\":200,\"timestamp\":\"" + actualTimestamp + "\",\"data\":{\"loans\":[],\"id\":1,\"amount\":2000,\"username\":\"sanjay\",\"approved\":false,\"loanType\":\"carLoan\"},\"error\":null}", result.getResponse().getContentAsString());
    }

    @Test
    public void testCreateLoan() throws Exception {
        List<LoanResponse> loanResponseList=new ArrayList<>();
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setAmount(7678L);
        loanResponse.setLoanType("carLoan");
        loanResponse.setUsername("Viswanth");
        loanResponse.setApproved(false);
        loanResponseList.add(loanResponse);
        when(loanService.create(Mockito.any())).thenReturn(loanResponseList);
        String requestBody="{\"id\":1,\"amount\": 2500.00,\"loanType\": \"car loan\",\"is_approved\": 0,\"user_id\": 3}";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/loan").content(requestBody).contentType(MediaType.APPLICATION_JSON).with(csrf());
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":[{\"loans\":[],\"id\":1,\"amount\":7678,\"username\":\"Viswanth\",\"approved\":false,\"loanType\":\"carLoan\"}],\"error\":null}",result.getResponse().getContentAsString());
    }

}
