package com.restapi.Service;

import com.restapi.dto.AccountDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.repository.AccountRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AccountRequest;
import com.restapi.response.AccountResponse;
import com.restapi.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    AccountDto accountDto;

    public List<Account> returnAccount() {
        List<Account> accountResponseList = new ArrayList<>();
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(1L);
        accountResponse.setName("viswanth");
        accountResponse.setAddress("Vijyawada");
        accountResponse.setAcc_no(7386641345L);
        accountResponse.setCity("Vjy");
        accountResponse.setState("Ap");
        accountResponse.setBalance(2000.0);

        return accountResponseList;
    }

    public List<AccountResponse> mapper() {
        List<AccountResponse> accountResponseList = new ArrayList<>();
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(1L);
        accountResponse.setName("viswanth");
        accountResponse.setCity("Vjy");
        accountResponse.setAddress("Vijayawada");
        accountResponse.setState("Ap");
        accountResponse.setBalance(2000.0);
        accountResponse.setAcc_no(7386641345L);
        accountResponse.setUsername("Viswanth");
        accountResponseList.add(accountResponse);
        return accountResponseList;

    }


    @Test
    public void testAllAccount() {
        when(accountRepository.findAll()).thenReturn(returnAccount());
        when(accountDto.mapToAccountResponseList(Mockito.any())).thenReturn(mapper());
        assertEquals(accountService.findAll().get(0).getId(), Long.valueOf(1L));
        assertEquals(accountService.findAll().get(0).getName(), "viswanth");
    }

    @Test
    public void testFindById() {
        Account account = new Account();
        account.setId(1L);
        account.setName("viswanth");
        account.setAddress("Vijayawada");
        account.setCity("vizay");
        account.setState("Ap");
        account.setAcc_no(7386641345L);
        account.setBalance(2000.0);

        when(accountRepository.findByUserId(1L)).thenReturn(Optional.of(account));
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(1L);
        accountResponse.setName("viswanth");
        accountResponse.setAddress("vijayawada");
        accountResponse.setCity("vizay");
        accountResponse.setState("Ap");
        accountResponse.setAcc_no(7386641345L);
        accountResponse.setBalance(2000.0);
        accountResponse.setUsername("viswanth");
        when(accountDto.mapToAccountResponse(Mockito.any())).thenReturn(accountResponse);
        AccountResponse actualResponse = accountService.findById(1L);
        System.out.println(actualResponse.getAcc_no());
        assertEquals(accountResponse, actualResponse);
    }

    @Test
    public void testCreateAccount() {
        AppUser user = new AppUser();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUser_id(1);
        Account account = new Account();
        account.setId(1L);
        account.setName("viswanth");
        account.setAddress("Vijayawada");
        account.setCity("vizay");
        account.setState("Ap");
        account.setAcc_no(7386641345L);
        account.setBalance(2000.0);
        when(accountDto.mapToAccount(Mockito.any())).thenReturn(account);
        Account createdAccount = accountService.createAccount(accountRequest);
        assertEquals(createdAccount.getId(), 1);
    }

    @Test
    public void testUpdateAccount() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUser_id(1);
        Account account = new Account();
        account.setId(1L);
        account.setName("viswanth");
        account.setAddress("Vijayawada");
        account.setCity("vizay");
        account.setState("Ap");
        account.setAcc_no(7386641345L);
        account.setBalance(2000.0);
        when(accountDto.mapToAccount(Mockito.any())).thenReturn(account);
        when(accountService.updateAccount(accountRequest)).thenReturn(mapper());
        List<AccountResponse> UpdatedAccount = accountService.updateAccount(accountRequest);
        assertEquals(UpdatedAccount.get(0).getId(), 1L);
        assertEquals(UpdatedAccount.get(0).getName(), "viswanth");
    }


    @Test
    public void testDeleteAccount() {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setUser_id(1);
        Account account = new Account();
        account.setId(1L);
        account.setName("viswanth");
        account.setAddress("Vijayawada");
        account.setCity("vizay");
        account.setState("Ap");
        account.setAcc_no(7386641345L);
        account.setBalance(2000.0);
        AppUser appUser = new AppUser();
        appUser.setUsername("Viswanth");
        account.setAppUser(appUser);
        when(accountRepository.findAll()).thenReturn(Collections.singletonList(account));
        when(accountService.deleteById(Mockito.anyInt())).thenReturn(mapper());
        List<AccountResponse> deleteAccount = accountService.deleteById(1);
        assertEquals("viswanth", deleteAccount.get(0).getName());
        assertEquals(1, deleteAccount.get(0).getId());
    }

    @Test
    void findByIdException() {
        Long appuserId = 123L;
        when(accountRepository.findByUserId(appuserId)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            accountService.findById(appuserId);
        });
    }

}








