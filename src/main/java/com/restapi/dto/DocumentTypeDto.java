package com.restapi.dto;

import com.restapi.model.DocumentType;
import com.restapi.model.Loan;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentTypeDto {

    @Autowired
    private UserRepository userRepository;
    public List<DocumentTypeResponse> mapToDocumentTypeResponse(List<DocumentType> documentTypes){
       List<DocumentTypeResponse> documentTypeResponses=new ArrayList<>();
       for(DocumentType documentType:documentTypes){
           DocumentTypeResponse documentTypeResponse=new DocumentTypeResponse();
           documentTypeResponse.setDocumentName(documentType.getDocumentName());
           documentTypeResponse.setBoolean(documentType.getIsBoolean());
           documentTypeResponse.setId(documentType.getId());
           documentTypeResponse.setUser_id(documentType.getAppUser().getId());
           documentTypeResponse.setDocumentFile(documentType.getDocumentFile());
           documentTypeResponses.add(documentTypeResponse);
       }
       return documentTypeResponses;

    }
    public DocumentType mapToDocumentType(DocumentTypeRequest documentTypeRequest) {
        DocumentType documentType= new DocumentType();
        if (documentType.getId()!=null) {
            documentType.setId(documentTypeRequest.getId());
        }
        documentType.setDocumentName(documentTypeRequest.getDocumentName());
        documentType.setIsBoolean(documentTypeRequest.isBoolean());
        return documentType;
    }

    public DocumentTypeResponse mapToDocumentResponse(DocumentType documentType) {
        DocumentTypeResponse documentTypeResponse = new DocumentTypeResponse();
        documentTypeResponse.setId(documentType.getId());
        documentTypeResponse.setDocumentName(documentType.getDocumentName());
        documentTypeResponse.setBoolean(documentType.getIsBoolean());
        documentTypeResponse.setDocumentFile(documentType.getDocumentFile());
        return documentTypeResponse;
    }
}
