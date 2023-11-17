package com.restapi.service;

import com.restapi.dto.DocumentDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Document;
import com.restapi.model.DocumentType;
import com.restapi.repository.DocumentRepository;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentRequest;
import com.restapi.response.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.List;


@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private DocumentDto documentDto;

    public DocumentResponse findall() {
        List<Document> documentList = documentRepository.findAll();
        return documentDto.mapToDocumentResponse(documentList);
    }

    @Transactional
    public DocumentResponse create(DocumentRequest documentRequest) {
        Document document = documentDto.mapToDocument(documentRequest);
        AppUser appUser = userRepository.findById(documentRequest.getUser_id())
                .orElseThrow(() -> new ResourceNotFoundException("user_id", "user_id", documentRequest.getUser_id()));
        DocumentType documentType=documentTypeRepository.findById(Math.toIntExact((documentRequest.getType_id())))
                        .orElseThrow(()->new ResourceNotFoundException("type_id","type_id",documentRequest.getType_id()));


        document.setAppUser(appUser);
        document.setDocumentType(documentType);
        documentRepository.save(document);

        return findall();


    }
}
