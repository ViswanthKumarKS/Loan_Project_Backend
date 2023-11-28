package com.restapi.dto;

import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.repository.UserRepository;
import com.restapi.request.AccountRequest;
import com.restapi.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountDto {
    @Autowired
    private UserRepository userRepository;

    public List<AccountResponse> mapToAccountResponse(List<Account> accounts) {
        List<AccountResponse> accountResponseList = new ArrayList<>();
        for (Account account : accounts){
            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setId(Long.valueOf(account.getId()));
            String user = account.getAppUser().getUsername();
            accountResponse.setName(user);
            accountResponse.setCity(account.getCity());
            accountResponse.setAddress(account.getAddress());
            accountResponse.setState(account.getState());
            accountResponse.setBalance(account.getBalance());
            accountResponse.setAcc_no(account.getAcc_no());
            accountResponse.setUsername(user);
            accountResponseList.add(accountResponse);
        }
        return accountResponseList;



    }
    public Account mapToAccount(AccountRequest accountRequest) {
        Account account = new Account();
        if (accountRequest.getId()!=null) {
            account.setId(accountRequest.getId());
        }
        account.setAcc_no(accountRequest.getAcc_no());
        account.setBalance(accountRequest.getBalance());
        account.setName(accountRequest.getName());
        account.setAddress(accountRequest.getAddress());
        account.setState(accountRequest.getState());
        account.setCity(accountRequest.getCity());
        return account;
    }


    public AccountResponse mapToAccountResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setName(account.getAppUser().getName());
        accountResponse.setCity(account.getCity());
        accountResponse.setAddress(account.getAddress());
        accountResponse.setState(account.getState());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setAcc_no(account.getAcc_no());
        accountResponse.setUsername(account.getAppUser().getName());
        return accountResponse;
    }
}
