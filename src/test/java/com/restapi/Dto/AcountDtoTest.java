package com.restapi.Dto;

import com.restapi.dto.AccountDto;
import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.model.Loan;
import com.restapi.repository.UserRepository;
import com.restapi.request.AccountRequest;
import com.restapi.request.LoanRequest;
import com.restapi.response.AccountResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AcountDtoTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    AccountDto accountDto;

    @Test
    public void testMapToAccountResponseList(){
        List<Account> accounts=new ArrayList<>();
        Account account=new Account();
        account.setId(1L);
        account.setName("Viswanth");
        account.setAddress("Vijayawada");
        account.setCity("vizay");
        account.setState("Ap");
        account.setAcc_no(7386641345L);
        account.setBalance(2000.0);
        AppUser user = new AppUser();
        user.setUsername("viswanth");
        account.setAppUser(user);
        accounts.add(account);
        List<AccountResponse> accountResponseList=accountDto.mapToAccountResponseList(accounts);
        assertEquals(1,accountResponseList.size());
        assertEquals("viswanth",accountResponseList.get(0).getUsername());
    }

    @Test
    public void testMapToAccount(){
        AccountRequest accountRequest=new AccountRequest();
        accountRequest.setId(1L);
        accountRequest.setName("Viswanth");
        accountRequest.setAddress("Vijayawada");
        accountRequest.setCity("vizay");
        accountRequest.setState("Ap");
        accountRequest.setAcc_no(7386641345L);
        accountRequest.setBalance(2000.0);
        Account account=accountDto.mapToAccount(accountRequest);
        assertEquals(7386641345L,account.getAcc_no());
    }

    @Test
    public void testmapToAccountResponse(){
        Account account=new Account();
        account.setId(1L);
        account.setName("Viswanth");
        account.setAddress("Vijayawada");
        account.setCity("vizay");
        account.setState("Ap");
        account.setAcc_no(7386641345L);
        account.setBalance(2000.0);
        AppUser user = new AppUser();
        user.setUsername("viswanth");
        account.setAppUser(user);
        AccountResponse accountResponse=accountDto.mapToAccountResponse(account);
        assertEquals(accountResponse.getBalance(),2000.0);
    }
    @Test
    public void testAccountIdNotNull() {
        Account account = new Account();
        account.setId(1L);
       AccountRequest accountRequest= new AccountRequest();
        accountRequest.setId(2L);
        if (account.getId() != null) {
            account.setId(accountRequest.getId());
        }
        assertEquals(accountRequest.getId(), account.getId());
    }



}
