package com.restapi.service;

import com.restapi.dto.DocumentTypeDto;
import com.restapi.model.DocumentType;
import com.restapi.model.Loan;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private DocumentTypeDto documentTypeDto;
    public DocumentTypeResponse findall() {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        return documentTypeDto.mapToDocumentTypeResponse(documentTypes);
    }

    public DocumentTypeResponse create(DocumentTypeRequest documentTypeRequest) {
        DocumentType documentType = documentTypeDto.mapToDocumentType(documentTypeRequest);
        documentTypeRepository.save(documentType);
        return findall();
    }







    public DocumentTypeResponse update(DocumentTypeRequest documentTypeRequest) {
        DocumentType documentType = documentTypeDto.mapToDocumentType(documentTypeRequest);
        documentTypeRepository.save(documentType);
        return findall();
    }
}
