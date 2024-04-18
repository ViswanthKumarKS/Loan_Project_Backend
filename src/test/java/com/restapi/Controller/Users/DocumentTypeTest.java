package com.restapi.Controller.Users;

import com.restapi.controller.Users.DocumentTypeController;
import com.restapi.model.DocumentType;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.repository.UserRepository;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
import com.restapi.service.StorageService;
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
@WebMvcTest(DocumentTypeController.class)
@WithMockUser(username = "user",password = "user",roles = "USER")
public class DocumentTypeTest {

    @Autowired
    MockMvc mockMvc;
    @SpyBean
    APIResponse apiResponse;

    @MockBean
    DocumentTypeService documentTypeService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    StorageService storageService;

    @MockBean
    DocumentTypeRepository documentTypeRepository;
    public List<DocumentType> returnDocument(){
        List<DocumentType> documentTypes=new ArrayList<>();
        DocumentTypeResponse documentTypeResponse=new DocumentTypeResponse();
        documentTypeResponse.setId(1L);
        documentTypeResponse.setDocumentFile("pdf");
        documentTypeResponse.setUsername("sanjay");
        documentTypeResponse.setDocumentName("Dummy");
        documentTypeResponse.setUser_id(3L);
        documentTypes.add(documentTypeResponse);
        return  documentTypes;
    }

    @Test
    public void testAllDocuments() throws Exception {
        when(documentTypeService.findall()).thenReturn(returnDocument());
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/DocumentType/all").accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+ actualTimestamp + "\",\"data\":[{\"id\":1,\"documentName\":\"Dummy\",\"createdAt\":null,\"isBoolean\":false,\"documentFile\":\"pdf\",\"user_id\":3,\"username\":\"sanjay\",\"boolean\":false}],\"error\":null}",result.getResponse().getContentAsString());
    }
    @Test
    public void testOneDocument() throws Exception {
        DocumentTypeResponse documentTypeResponse=new DocumentTypeResponse();
        documentTypeResponse.setId(1L);
        documentTypeResponse.setDocumentFile("pdf");
        documentTypeResponse.setUsername("sanjay");
        documentTypeResponse.setDocumentName("Dummy");
        documentTypeResponse.setUser_id(3L);
        when(documentTypeService.findById(3L)).thenReturn(documentTypeResponse);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/api/DocumentType/detail/3");
        MvcResult result=mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONObject actualJson = new JSONObject(result.getResponse().getContentAsString());
        String actualTimestamp = actualJson.getString("timestamp");
        assertEquals("{\"status\":200,\"timestamp\":\""+ actualTimestamp + "\",\"data\":{\"id\":1,\"documentName\":\"Dummy\",\"createdAt\":null,\"isBoolean\":false,\"documentFile\":\"pdf\",\"user_id\":3,\"username\":\"sanjay\",\"boolean\":false},\"error\":null}",result.getResponse().getContentAsString());
    }

}
