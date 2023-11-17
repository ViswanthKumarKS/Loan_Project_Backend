package com.restapi.dto;

import com.restapi.model.Document;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentRequest;
import com.restapi.response.DocumentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentDto {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    public DocumentResponse mapToDocumentResponse(List<Document> documents){
        return new DocumentResponse(documents);

    }
    public Document mapToDocument(DocumentRequest documentRequest) {
        Document document = new Document();
        if (documentRequest.getId()!=null) {
            document.setId(documentRequest.getId());
        }


        return document;
    }
}
