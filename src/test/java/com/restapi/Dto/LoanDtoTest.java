package com.restapi.Dto;

import com.restapi.dto.LoanDto;
import com.restapi.model.AppUser;
import com.restapi.model.Loan;
import com.restapi.request.LoanRequest;
import com.restapi.response.LoanResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoanDtoTest {

    @InjectMocks
    LoanDto loanDto;

    @Test
    public void testMapToLoanResponseList(){
        List<Loan> loan=new ArrayList<>();
        Loan loans=new Loan();
        loans.setId(1L);
        loans.setLoanType("CarLoan");
        loans.setAmount(2000L);
        loans.setApproved(false);
        AppUser appUser=new AppUser();
        appUser.setUsername("Viswanth");
        loans.setAppUser(appUser);
        loan.add(loans);
        List<LoanResponse> loanResponse= loanDto.mapToLoanResponseList(loan);
        assertEquals(loanResponse.get(0).getId(),1L);

    }
    @Test
    public void testMapToLoan() {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setId(1L);
        loanRequest.setApproved(false);
        loanRequest.setAmount(2000L);
        loanRequest.setLoanType("CarLoan");
        Loan loan=loanDto.mapToLoan(loanRequest);
       assertEquals(loan.getLoanType(),"CarLoan");
    }

    @Test
    public void testMapToLoanResponse(){
        Loan loan=new Loan();
        loan.setId(1L);
        AppUser appUser=new AppUser();
        appUser.setUsername("Viswanth");
        loan.setAppUser(appUser);
        loan.setAmount(2000L);
        loan.setLoanType("CarLoan");
        loan.setApproved(false);
        LoanResponse loanResponse=loanDto.mapToLoanResponse(loan);
        assertEquals(loanResponse.getId(),1L);
    }
    @Test
    public void testLoanIdNotNull() {
        Loan loan = new Loan();
        loan.setId(1L);
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setId(2L);
        if (loan.getId() != null) {
            loan.setId(loanRequest.getId());
        }
        assertEquals(loanRequest.getId(), loan.getId());
    }

}
