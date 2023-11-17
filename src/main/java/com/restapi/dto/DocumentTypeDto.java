package com.restapi.dto;

import com.restapi.model.Document;
import com.restapi.model.DocumentType;
import com.restapi.request.DocumentRequest;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentResponse;
import com.restapi.response.DocumentTypeResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentTypeDto {
    public DocumentTypeResponse mapToDocumentTypeResponse(List<DocumentType> documentTypes){
        return new DocumentTypeResponse(documentTypes);

    }
    public DocumentType mapToDocumentType(DocumentTypeRequest documentTypeRequest) {
        DocumentType documentType= new DocumentType();
        if (documentType.getId()!=null) {
            documentType.setId(documentTypeRequest.getId());
        }
        documentType.setTypeName(documentTypeRequest.getTypeName());

        return documentType;
    }
}
