package com.restapi.controller.Users;

import com.restapi.model.DocumentType;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/DocumentType")
public class DocumentTypeController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllDocuments(){
        DocumentTypeResponse documentTypeResponse=documentTypeService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentTypeResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<APIResponse> createDocument(@RequestBody DocumentTypeRequest documentTypeRequest){
        DocumentTypeResponse documentTypeResponse=documentTypeService.create(documentTypeRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentTypeResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @PutMapping()
    public ResponseEntity<APIResponse> UPDATEDocument(@RequestBody DocumentTypeRequest documentTypeRequest) {
        DocumentTypeResponse documentTypeResponse=documentTypeService.update(documentTypeRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentTypeResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
