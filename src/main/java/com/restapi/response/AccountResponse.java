package com.restapi.response;

import com.restapi.model.Account;
import com.restapi.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Getter

public class AccountResponse {

    private List<AccountRequest> accountList=new ArrayList<>();

    public AccountResponse(List<Account> account) {
    }
}
