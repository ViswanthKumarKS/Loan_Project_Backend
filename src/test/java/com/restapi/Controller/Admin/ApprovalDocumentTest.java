package com.restapi.Controller.Admin;

import com.restapi.controller.Admin.ApprovalDocument;
import com.restapi.model.DocumentType;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ApprovalDocument.class)
@WithMockUser(username = "admin",password = "admin",roles = "ADMIN")
public class ApprovalDocumentTest {

    @Autowired
    MockMvc mockMvc;
    @SpyBean
    APIResponse apiResponse;

    @MockBean
    DocumentTypeService documentTypeService;
    public  List<DocumentTypeResponse> returnApproval(){
        List<DocumentTypeResponse> dummyData = new ArrayList<>();
        DocumentTypeResponse document = new DocumentTypeResponse();
        document.setId(1L);
        document.setDocumentName("dummy.pdf");
        document.setDocumentFile("1711950988272-file.pdf");
        document.setIsBoolean(true);
        dummyData.add(document);
        return dummyData;

    }
    @Test
    public void testNotApproval() throws Exception {
        when(documentTypeService.notApproval()).thenReturn(returnApproval());
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/admin/document/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":[{\"id\":1,\"documentName\":\"dummy.pdf\",\"createdAt\":null,\"isBoolean\":true,\"documentFile\":\"1711950988272-file.pdf\",\"user_id\":null,\"username\":null,\"boolean\":false}],\"error\":null}",result.getResponse().getContentAsString());

    }
    @Test
    public void testApproval() throws Exception {
        Long id = 1L;
        String approval = "Approved";
        when(documentTypeService.approval(id)).thenReturn(approval);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.put("/api/admin/document/approval/1").contentType(MediaType.APPLICATION_JSON).with(csrf());
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+actualTimestamp+"\",\"data\":\"Approved\",\"error\":null}",result.getResponse().getContentAsString());
    }
    @Test
    public void testDownloadFile() throws Exception {
        File mockFile = File.createTempFile("mockFile", ".txt");
        FileInputStream fileInputStream = new FileInputStream(mockFile);
        when(documentTypeService.getFile(123L)).thenReturn(mockFile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/admin/document/downloadFile/123")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }
}
