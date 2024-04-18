package com.restapi.service;

import com.restapi.dto.AccountDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.repository.AccountRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AccountRequest;
import com.restapi.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountDto accountDto;


    public List<AccountResponse> findAll() {
        List<Account> accounts = accountRepository.findAll();
        return accountDto.mapToAccountResponseList(accounts);


    }


    public Account createAccount(AccountRequest accountRequest) {
        Account account = accountDto.mapToAccount(accountRequest);
        System.out.println(account.getId());
        AppUser appUser = userRepository.findById(Long.valueOf(accountRequest.getUser_id()))
                .orElseThrow(() -> new ResourceNotFoundException("user_id", "user_id", accountRequest.getUser_id()));
        account.setAppUser(appUser);
        System.out.println(appUser.getId());


        accountRepository.save(account);
        return account;

    }

    public List<AccountResponse> updateAccount(AccountRequest accountRequest) {
        Account account = accountDto.mapToAccount(accountRequest);
        accountRepository.save(account);
        return findAll();

    }

    public List<AccountResponse> deleteById(Integer id) {
        accountRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public AccountResponse findById(Long userId) {
        Account account = accountRepository.findByUserId(userId).
                orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", userId)));
        AccountResponse accountResponse = accountDto.mapToAccountResponse(account);
        return accountResponse;
    }


}
