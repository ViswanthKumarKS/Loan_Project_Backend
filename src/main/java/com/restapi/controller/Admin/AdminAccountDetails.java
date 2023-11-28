package com.restapi.controller.Admin;

import com.restapi.model.Account;
import com.restapi.model.Role;
import com.restapi.repository.AccountRepository;
import com.restapi.response.common.APIResponse;
import com.restapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/account")
@RolesAllowed({Role.ADMIN})
public class AdminAccountDetails {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;



    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllAccounts(){
        List<Account> accountList = accountRepository.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(accountList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

}
