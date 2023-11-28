package com.restapi.controller.Admin;

import com.restapi.model.DocumentType;
import com.restapi.request.ApprovalRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/document")
public class ApprovalDocument {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private DocumentTypeService documentTypeService;

    @PutMapping("/approval/{id}")
    public ResponseEntity<APIResponse>getdocumentApproval(@Valid  @PathVariable Long id){
        String approval=documentTypeService.approval(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(approval);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @GetMapping("/all")
    public ResponseEntity<APIResponse>getNotApproval() {
        List<DocumentType> approval = documentTypeService.notApproval();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(approval);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long id) throws IOException {

        File file = documentTypeService.getFile(id);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

}
