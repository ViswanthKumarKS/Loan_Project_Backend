package com.restapi.controller.Users;

import com.restapi.model.Account;
import com.restapi.model.Role;
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

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RolesAllowed({Role.USER})
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllAccounts() {
        List<AccountResponse> accountList = accountService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/detail/{user_id}")
    public ResponseEntity<APIResponse> getAccount(@Valid @PathVariable Long user_id) {
        AccountResponse accountResponse = accountService.findById(user_id);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        Account accountResponse = (Account) accountService.createAccount(accountRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateAccount(@Valid @RequestBody AccountRequest accountRequest) {
        List<AccountResponse> accountResponse = accountService.updateAccount(accountRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteById(@Valid @PathVariable Integer id) {
        List<AccountResponse> accounts = accountService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accounts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }


}


