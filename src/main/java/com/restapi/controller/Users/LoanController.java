package com.restapi.controller.Users;

import com.restapi.model.Loan;
import com.restapi.request.LoanRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.LoanResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllLoans(){
        List<LoanResponse> loan = loanService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loan);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@Valid @RequestBody LoanRequest loanRequest){
        List<LoanResponse> loans =loanService.create(loanRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loans);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }
    @GetMapping("/detail/{user_id}")
    public ResponseEntity<APIResponse> getLoan(@Valid @PathVariable Long user_id) {
        LoanResponse loanResponse = loanService.findById(user_id);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loanResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
