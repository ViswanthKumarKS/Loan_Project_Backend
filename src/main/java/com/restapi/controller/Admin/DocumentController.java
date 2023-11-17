package com.restapi.controller.Admin;


import com.restapi.model.Account;
import com.restapi.model.Document;
import com.restapi.request.DocumentRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.DocumentResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {


    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private DocumentService documentService;



    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllDocuments(){
        DocumentResponse documentResponse =documentService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<APIResponse> createDocuments(@RequestBody DocumentRequest documentRequest) {
        DocumentResponse documentResponse = documentService.create(documentRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(documentResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
