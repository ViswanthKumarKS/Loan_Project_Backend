package com.restapi.controller.Admin;

import com.restapi.model.DocumentType;
import com.restapi.model.Loan;

import com.restapi.response.LoanResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.DocumentTypeService;
import com.restapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class LoanApproval {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getNotApproval() {
        List<LoanResponse> approval = loanService.notApproval();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(approval);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/approval/{id}")

    public ResponseEntity<APIResponse>getApproval(@Valid @PathVariable Long id){
        String approval=loanService.approval(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(approval);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
}
