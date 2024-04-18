package com.restapi.Controller.Admin;

import com.restapi.controller.Admin.LoanApproval;
import com.restapi.response.LoanResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.LoanService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(LoanApproval.class)
@WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
public class LoanApprovalTest {

    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;

    @MockBean
    LoanService loanService;


    public List<LoanResponse> returnApproval(){
        List<LoanResponse> loanResponseList=new ArrayList<>();
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setLoanType("CarLoan");
        loanResponse.setAmount(2000L);
        loanResponse.setApproved(true);
        loanResponse.setUsername("Viswanth");
        loanResponseList.add(loanResponse);
        return loanResponseList;
    }
    @Test
    public void testNotApproval() throws Exception {
        when(loanService.notApproval()).thenReturn(returnApproval());
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/admin/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":[{\"loans\":[],\"id\":1,\"amount\":2000,\"username\":\"Viswanth\",\"approved\":true,\"loanType\":\"CarLoan\"}],\"error\":null}",result.getResponse().getContentAsString());

    }
    @Test
    public void testGetApproval() throws Exception {
        Long id = 1L;
        String approval = "Approved";
        when(loanService.approval(id)).thenReturn(approval);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.put("/api/admin/approval/1").contentType(MediaType.APPLICATION_JSON).with(csrf());
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":\"Approved\",\"error\":null}",result.getResponse().getContentAsString());




    }

}
