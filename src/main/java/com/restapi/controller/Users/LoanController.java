package com.restapi.controller.Users;

import com.restapi.request.LoanRequest;
import com.restapi.response.DocumentResponse;
import com.restapi.response.LoanResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private LoanService loanService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllLoans(){
        LoanResponse loanResponse =loanService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loanResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<APIResponse> create(@RequestBody LoanRequest loanRequest){
        LoanResponse loanResponse=loanService.create(loanRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loanResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);


    }

}
