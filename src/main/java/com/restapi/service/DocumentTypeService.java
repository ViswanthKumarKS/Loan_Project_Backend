package com.restapi.service;

import com.restapi.dto.DocumentTypeDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.DocumentType;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentTypeResponse;
import io.github.classgraph.Resource;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentTypeDto documentTypeDto;

    @Autowired
    private StorageService storageService;

    public List<DocumentType> findall() {
        List<DocumentType> documentTypes = documentTypeRepository.findAll();
        List<DocumentTypeResponse> documentTypeResponses = documentTypeDto.mapToDocumentTypeResponse(documentTypes);
        return documentTypeRepository.findAll();

    }

    public List<DocumentType> create(DocumentType documentType, Long userId) {
        try {
            AppUser appUser = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("user_id", "user_id", userId));
            documentType.setAppUser(appUser);
            documentTypeRepository.save(documentType);

            // Ensure changes are persisted before fetching the updated list
            documentTypeRepository.flush();

            return findall();
        } catch (Exception e) {
            throw new ServiceException("Error creating document", e);
        }
    }


    public List<DocumentType> update(DocumentTypeRequest documentTypeRequest) {
        DocumentType documentType = documentTypeDto.mapToDocumentType(documentTypeRequest);
        documentTypeRepository.save(documentType);
        return findall();
    }

    public String approval(Long id) {
        documentTypeRepository.updateUser(id);
        return "success";

    }

    public List<DocumentTypeResponse> notApproval() {
        List<DocumentType> documentTypes = documentTypeRepository.findNotApproval();

        return documentTypeDto.mapToDocumentTypeResponse(documentTypes);
    }

    public DocumentTypeResponse findById(Long userId) {
        DocumentType documentType = documentTypeRepository.findByUserId(userId).
                orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", userId)));
        AppUser user = userRepository.findById(userId).
                orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", userId)));
        DocumentTypeResponse documentTypeResponse = documentTypeDto.mapToDocumentResponse(documentType);
        documentTypeResponse.setUser_id(user.getId());
        return documentTypeResponse;
    }

    public File getFile(Long id) throws IOException {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        UrlResource resource = storageService.loadFileAsResource(documentType.getDocumentFile());

        return resource.getFile();

    }
}
