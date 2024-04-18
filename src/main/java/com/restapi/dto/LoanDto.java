package com.restapi.dto;

import com.restapi.model.Account;
import com.restapi.model.DocumentType;
import com.restapi.model.Loan;
import com.restapi.repository.UserRepository;
import com.restapi.request.AccountRequest;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.request.LoanRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanDto {
    @Autowired
    private UserRepository userRepository;

    public List<LoanResponse> mapToLoanResponseList(List<Loan> loans) {
        List<LoanResponse> loanResponseList = new ArrayList<>();
        for (Loan loan : loans) {
            LoanResponse loanResponse = new LoanResponse();
            loanResponse.setId(loan.getId());
            String user = loan.getAppUser().getName();
            loanResponse.setUsername(user);

            loanResponse.setAmount(loan.getAmount());
            loanResponse.setLoanType(loan.getLoanType());
            loanResponse.setApproved(loan.isApproved());


            loanResponseList.add(loanResponse);
        }
        return loanResponseList;
    }

    public Loan mapToLoan(LoanRequest loanRequest) {
        Loan loan = new Loan();
        if (loan.getId()!=null) {
            loan.setId(loanRequest.getId());
        }
        loan.setApproved(loanRequest.isApproved());
        loan.setAmount(loanRequest.getAmount());
        loan.setLoanType(loanRequest.getLoanType());
        return loan;
    }


        public LoanResponse mapToLoanResponse(Loan loans) {
            LoanResponse loanResponse = new LoanResponse();

            loanResponse.setId(loans.getId());

            loanResponse.setUsername(loans.getAppUser().getName());

            loanResponse.setAmount(loans.getAmount());
            loanResponse.setLoanType(loans.getLoanType());
            loanResponse.setApproved(loans.isApproved());


            return loanResponse;
        }
}

