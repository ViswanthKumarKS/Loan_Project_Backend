package com.restapi.controller.Users;

import com.restapi.model.Account;
import com.restapi.repository.AccountRepository;
import com.restapi.request.AccountRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AccountService;
import io.swagger.v3.oas.models.responses.ApiResponse;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllAccounts(){
        AccountResponse accountResponse=accountService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }
    @GetMapping("/{user_id}")
    public ResponseEntity<APIResponse> getAccount(@PathVariable Integer user_id){
        AccountResponse accountResponse=accountService.findById(user_id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }




    @PostMapping
    public ResponseEntity<APIResponse> createAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.createAccount(accountRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<APIResponse> updateAccount(@RequestBody AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.updateAccount(accountRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

        @DeleteMapping("/{id}")
                public ResponseEntity<APIResponse>deleteById(@PathVariable Integer   id ){
        AccountResponse accounts= accountService.deleteById(id);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(accounts);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }


}


