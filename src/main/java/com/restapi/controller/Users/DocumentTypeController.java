package com.restapi.controller.Users;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.DocumentType;
import com.restapi.repository.DocumentTypeRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/DocumentType")
public class DocumentTypeController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private DocumentTypeService documentTypeService;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StorageService storageService;
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllDocuments() {
        List<DocumentType> documentTypes = documentTypeService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentTypes);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createDocument(
            @RequestParam("documentFile") MultipartFile documentFile,

            @RequestParam("documentName") String documentName,
            @RequestParam("user_id") Long userId

    ) {
        APIResponse apiResponse = new APIResponse();

        try {

            System.out.println("documentFile: " + documentFile.getOriginalFilename());

            System.out.println("documentName: " + documentName);
            System.out.println("user_id: " + userId);

            String file =storageService.storeFile(documentFile);




            DocumentType documentType = new DocumentType();
            documentType.setDocumentFile(file);

            documentType.setDocumentName(documentName);

            AppUser appUser = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("user_id", "user_id", userId));
            documentType.setAppUser(appUser);
            documentTypeRepository.save(documentType);

            List<DocumentType> documentTypes = documentTypeService.create(documentType, userId);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(documentTypes);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);


        }
    }
    @PutMapping()
    public ResponseEntity<APIResponse> UPDATEDocument(@Valid @RequestBody DocumentTypeRequest documentTypeRequest) {
        List<DocumentType> documentTypes=documentTypeService.update(documentTypeRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentTypes);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/detail/{user_id}")
    public ResponseEntity<APIResponse> getdocument(@Valid @PathVariable Long user_id) {
        DocumentTypeResponse documentTypeResponse = documentTypeService.findById(user_id);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentTypeResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }





}
