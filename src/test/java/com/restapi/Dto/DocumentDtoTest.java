package com.restapi.Dto;

import com.restapi.dto.DocumentTypeDto;
import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.model.DocumentType;
import com.restapi.request.AccountRequest;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentTypeResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DocumentDtoTest {

    @InjectMocks
    DocumentTypeDto documentTypeDto;

    @Test
    public void testMapToDocumentTypeResponse(){
        List<DocumentType> documentTypes=new ArrayList<>();
        DocumentType documentType=new DocumentType();
        documentType.setId(1L);
        documentType.setDocumentName("Dummy");
        documentType.setDocumentFile("1234567.pdf");
        documentType.setIsBoolean(false);
        AppUser appUser=new AppUser();
        appUser.setUsername("Viswanth");
        documentType.setAppUser(appUser);
        documentTypes.add(documentType);
        List<DocumentTypeResponse> documentTypeResponses=documentTypeDto.mapToDocumentTypeResponse(documentTypes);
        assertEquals(documentTypeResponses.get(0).getId(),1L);
    }

    @Test
    public void  testMapToDocumentType(){
        DocumentTypeRequest documentTypeRequest=new DocumentTypeRequest();
        documentTypeRequest.setUser_id(1L);
        documentTypeRequest.setId(1L);
        documentTypeRequest.setDocumentName("dummy");
        documentTypeRequest.setDocumentFile("1234567.pdf");
        documentTypeRequest.setBoolean(false);
        DocumentType documentType=documentTypeDto.mapToDocumentType(documentTypeRequest);
        assertEquals(documentType.getIsBoolean(),false);
    }

    @Test
    public void testMapToDocumentResponse(){
        DocumentType documentType=new DocumentType();
        documentType.setId(1L);
        documentType.setDocumentName("Dummy");
        documentType.setDocumentFile("1234567.pdf");
        documentType.setIsBoolean(false);
        DocumentTypeResponse documentTypeResponse=documentTypeDto.mapToDocumentResponse(documentType);
        assertEquals(documentTypeResponse.getId(),1L);
    }
    @Test
    public void testDocumentIdNotNull() {
        DocumentType documentType = new DocumentType();
        documentType.setId(1L);
        DocumentTypeRequest documentTypeRequest=new DocumentTypeRequest();
        documentTypeRequest.setId(2L);
        if (documentType.getId() != null) {
           documentType.setId(documentTypeRequest.getId());
        }
        assertEquals(documentTypeRequest.getId(), documentType.getId());
    }
}
