package com.restapi.Controller.Users;

import com.restapi.controller.Users.DocumentTypeController;
import com.restapi.controller.Users.DownloadController;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DownloadController.class)
@WithMockUser(username = "user",password = "user",roles = "USER")
public class DownloadTest {
    @Autowired
    MockMvc mockMvc;

    @SpyBean
    APIResponse apiResponse;
    @MockBean
    DocumentTypeService documentTypeService;


    @Test
    public void testDownloadFile() throws Exception {
        File mockFile = File.createTempFile("mockFile", ".txt");
        FileInputStream fileInputStream = new FileInputStream(mockFile);
        when(documentTypeService.getFile(123L)).thenReturn(mockFile);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/DocumentType/downloadFile/123")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(result.getResponse().getContentAsString());
    }

}
