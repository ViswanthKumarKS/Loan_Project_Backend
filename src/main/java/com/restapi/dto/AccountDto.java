package com.restapi.dto;

import com.restapi.model.Account;
import com.restapi.repository.UserRepository;
import com.restapi.request.AccountRequest;
import com.restapi.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDto {
    @Autowired
    private UserRepository userRepository;

    public AccountResponse mapToAccountResponse(List<Account> account){
        return new AccountResponse(account);

    }
    public Account mapToAccount(AccountRequest accountRequest) {
        Account account = new Account();
        if (accountRequest.getId()!=null) {
            account.setId(accountRequest.getId());
        }
        account.setAcc_no(accountRequest.getAcc_no());
        account.setBalance(accountRequest.getBalance());
        return account;
    }

}
