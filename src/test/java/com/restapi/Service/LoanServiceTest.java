package com.restapi.Service;

import com.restapi.dto.LoanDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Loan;
import com.restapi.repository.LoanRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoanRequest;
import com.restapi.response.LoanResponse;
import com.restapi.service.LoanService;
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


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {

    @InjectMocks
    LoanService loanService;

    @Mock
    LoanDto loanDto;
    @Mock
    LoanRepository loanRepository;

    @Mock
    UserRepository userRepository;

    public List<Loan> returnLoan() {
        List<Loan> loans = new ArrayList<>();
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setLoanType("carLoan");
        loanResponse.setAmount(2000L);
        loanResponse.setUsername("Viswanth");
        loanResponse.setApproved(false);
        return loans;
    }

        public List<LoanResponse> mapper(){
        List<LoanResponse>  loanResponseList=new ArrayList<>();
            LoanResponse loanResponse = new LoanResponse();
            loanResponse.setId(1L);
            loanResponse.setUsername("viswanth");
            loanResponse.setAmount(2000L);
            loanResponse.setLoanType("carLoan");
            loanResponse.setApproved(false);
            loanResponseList.add(loanResponse);
            return loanResponseList;
    }

    @Test
    public void testAllLoans(){
        when(loanRepository.findAll()).thenReturn(returnLoan());
        when(loanDto.mapToLoanResponseList(Mockito.any())).thenReturn(mapper());
        assertEquals(loanService.findall().get(0).getId(),1L);
        assertEquals(loanService.findall().get(0).getLoanType(),"carLoan");
    }
    @Test
    public void testFindById(){
        Loan loan=new Loan();
        loan.setId(1L);
        loan.setLoanType("CarLoan");
        loan.setAmount(2000L);
        loan.setApproved(false);
        when(loanRepository.findByUserId(1L)).thenReturn(Optional.of(loan));
        LoanResponse loanResponse=new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setUsername("Viswanth");
        loanResponse.setAmount(2000L);
        loanResponse.setLoanType("CarLoan");
        loanResponse.setApproved(false);
        when(loanDto.mapToLoanResponse(Mockito.any())).thenReturn(loanResponse);
        LoanResponse actualResponse=loanService.findById(1L);
        assertEquals(loanResponse,actualResponse);
    }
    public Loan loan(){
        List<Loan> loanResponseList=new ArrayList<>();
        Loan loan=new Loan();
        loan.setId(1L);
        loan.setApproved(false);
        loan.setAmount(2000L);
        loan.setLoanType("CarLoan");
        loanResponseList.add(loan);
        return (Loan) loanResponseList;


    }
    @Test
    public void testCreateLoan(){
        AppUser user = new AppUser();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setUser_id(1L);
        Loan loan=new Loan();
        loan.setId(1L);
        loan.setLoanType("CarLoan");
        loan.setAmount(2000L);
        loan.setApproved(false);
        when(loanDto.mapToLoan(Mockito.any())).thenReturn(loan);
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setId(1L);
        loanResponse.setLoanType("CarLoan");
        loanResponse.setAmount(2000L);
        loanResponse.setApproved(false);
        when(loanService.create(loanRequest)).thenReturn(Collections.singletonList(loanResponse));
        List<LoanResponse> actualResponse=loanService.create(loanRequest);
        assertEquals(loan.getId(),actualResponse.get(0).getId());
    }

    @Test
    public void testNotApproval(){
        when(loanRepository.findNotApproval()).thenReturn(returnLoan());
        when(loanDto.mapToLoanResponseList(Mockito.any())).thenReturn(mapper());
        assertEquals(loanService.notApproval().get(0).getId(),1L);
        assertEquals(loanService.notApproval().get(0).isApproved(),false);
    }

    @Test
    public void testApproval(){
        Long userId=1L;
        String result=loanService.approval(userId);
        assertEquals("success",result);
    }
    @Test
    public void testCreateException(){
        when(userRepository.findById(1L)).thenThrow(new ResourceNotFoundException("user_id","user_id",1L));
        Exception exception=assertThrows(ResourceNotFoundException.class,()->{
            userRepository.findById(1L);
        });
        assertEquals("user_id not found with user_id : '1'",exception.getMessage());
    }

    @Test
    void findById_WhenLoanDoesNotExist_ShouldThrowResourceNotFoundException() {
        Long userId = 123L;
        when(loanRepository.findByUserId(userId)).thenReturn(java.util.Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            loanService.findById(userId);
        });
    }
    }
