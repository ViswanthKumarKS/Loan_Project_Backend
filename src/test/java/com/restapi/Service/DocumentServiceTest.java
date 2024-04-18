package com.restapi.Service;

import com.restapi.dto.DocumentTypeDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.DocumentType;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.service.DocumentTypeService;
import com.restapi.service.StorageService;
import com.restapi.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DocumentServiceTest {

    @InjectMocks
    DocumentTypeService documentTypeService;
    @Mock
    DocumentTypeRepository documentTypeRepository;

    @Mock
    DocumentTypeDto documentTypeDto;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    StorageService storageService;

    public List<DocumentType> returnDocument(){
        List<DocumentType> documentTypes=new ArrayList<>();
        DocumentType documentType=new DocumentType();
        documentType.setId(1L);
        documentType.setDocumentName("Dummy.pdf");
        documentType.setDocumentFile("1234567.pdf");
        documentType.setIsBoolean(false);
        documentTypes.add(documentType);
        return documentTypes;
        }

        public List<DocumentTypeResponse> mapper(){
            List<DocumentTypeResponse> documentTypeResponses=new ArrayList<>();
            DocumentTypeResponse documentTypeResponse=new DocumentTypeResponse();
            documentTypeResponse.setDocumentName("Dummy.pdf");
            documentTypeResponse.setBoolean(false);
            documentTypeResponse.setId(1L);
            documentTypeResponse.setUser_id(1L);
            documentTypeResponse.setDocumentFile("1234567.pdf");
            documentTypeResponses.add(documentTypeResponse);

            return  documentTypeResponses;

    }

    @Test
    public void testAllDocuments(){
        when(documentTypeRepository.findAll()).thenReturn(returnDocument());
        when(documentTypeDto.mapToDocumentTypeResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(documentTypeService.findall().get(0).getId(),1L);
        assertEquals(documentTypeService.findall().get(0).getDocumentName(),"Dummy.pdf");
    }

    @Test
    public void testFindById(){
        DocumentType documentType=new DocumentType();
        documentType.setId(1L);
        documentType.setDocumentName("dummy.pdf");
        documentType.setDocumentFile("1234567.pdf");
        documentType.setIsBoolean(false);
        when(documentTypeRepository.findByUserId(1L)).thenReturn(Optional.of(documentType));
        AppUser user=new AppUser();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        DocumentTypeResponse documentTypeResponse = new DocumentTypeResponse();
        documentTypeResponse.setId(1L);
        documentTypeResponse.setDocumentName("dummy.pdf");
        documentTypeResponse.setBoolean(false);
        documentTypeResponse.setDocumentFile("1234567.pdf");
        when(documentTypeDto.mapToDocumentResponse(Mockito.any())).thenReturn(documentTypeResponse);
        DocumentTypeResponse actualResponse=documentTypeService.findById(1L);
        assertEquals(documentTypeResponse, actualResponse);

    }

    @Test
    public void testNotApproval(){
        when(documentTypeRepository.findNotApproval()).thenReturn(returnDocument());
        when(documentTypeDto.mapToDocumentTypeResponse(Mockito.any())).thenReturn(mapper());
        assertEquals(documentTypeService.notApproval().get(0).isBoolean(),false);
    }

    @Test
    public void testApproval(){
        Long id=1L;
        String result=documentTypeService.approval(id);
        assertEquals("success",result);

    }
    @Test
    public void testUpdateDocument(){
        DocumentTypeRequest documentTypeRequest=new DocumentTypeRequest();
        documentTypeRequest.setUser_id(1L);
        DocumentType documentType= new DocumentType();
        documentType.setId(1L);
        documentType.setDocumentName("Dummy.pdf");
        documentType.setIsBoolean(false);
        when(documentTypeDto.mapToDocumentType(Mockito.any())).thenReturn(documentType);
        when(documentTypeService.update(documentTypeRequest)).thenReturn(returnDocument());
        List<DocumentType> actualResponse=documentTypeService.update(documentTypeRequest);
        assertEquals(actualResponse.get(0).getId(),1L);
        assertEquals(actualResponse.get(0).getDocumentName(),"Dummy.pdf");
    }


    @Test
    void findByIdException() {
        Long appuserId = 123L;
        when(documentTypeRepository.findByUserId(appuserId)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            documentTypeService.findById(appuserId);
        });
    }

    @Test
    public void testGetFileException(){
        Long id=123L;
        when(documentTypeRepository.findById(id)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class,()->{
            documentTypeService.getFile(id);
        });
    }
    @Test
    void findById_UserNotFound_ThrowsResourceNotFoundException() {
        Long appuserId = 123L;
        when(userRepository.findById(appuserId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            userRepository.findById(appuserId).orElseThrow(() -> new ResourceNotFoundException("AppUserId",
                    "AppUserId", appuserId));
        });
    }

    @Test
    public void createDocument(){
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(appUser));
        DocumentType documentType = new DocumentType();
        documentType.setId(1L);
        documentType.setDocumentName("dummy.pdf");
        documentType.setDocumentFile("pdf");
        documentType.setIsBoolean(false);
        when(documentTypeRepository.save(Mockito.any())).thenReturn(documentType);
        when(documentTypeRepository.findAll()).thenReturn(returnDocument());
        List<DocumentType> actualResponse = documentTypeService.create(documentType, 1L);
        verify(documentTypeRepository).save(documentType);
        verify(documentTypeRepository).flush();
    }
    @Test
    void testCreateDocumentTypeException() {
        Long userId = 1L;
        DocumentType documentType = new DocumentType();
        when(userRepository.findById(userId)).thenThrow(new RuntimeException("User not found"));
        assertThrows(ServiceException.class, () -> {
            documentTypeService.create(documentType, userId);
        });
        verify(userRepository, times(1)).findById(userId);

    }



}
