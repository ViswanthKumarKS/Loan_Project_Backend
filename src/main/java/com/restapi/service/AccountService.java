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




    public AccountResponse findAll() {
      List<Account> accountList=accountRepository.findAll();

      return accountDto.mapToAccountResponse(accountList);


    }




    public AccountResponse createAccount(AccountRequest accountRequest) {
        Account account = accountDto.mapToAccount(accountRequest);

        AppUser appUser=userRepository.findById(accountRequest.getUser_id())
                        .orElseThrow(()-> new ResourceNotFoundException("user_id","user_id",accountRequest.getUser_id()));
        account.setAppUser(appUser);


        accountRepository.save(account);
        return findAll();

    }

    public AccountResponse updateAccount(AccountRequest accountRequest) {
        Account account=accountDto.mapToAccount(accountRequest);
        accountRepository.save(account);
        return findAll();

    }

    public AccountResponse deleteById(Integer id) {
        accountRepository.deleteById(Long.valueOf(id));
        return findAll();
    }

    public AccountResponse findById(Integer userId) {
        accountRepository.findById(Long.valueOf(userId));
        return findById(userId);
    }
}
