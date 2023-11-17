package com.restapi.dto;

import com.restapi.model.DocumentType;
import com.restapi.model.Loan;
import com.restapi.repository.UserRepository;
import com.restapi.request.DocumentTypeRequest;
import com.restapi.request.LoanRequest;
import com.restapi.response.DocumentTypeResponse;
import com.restapi.response.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanDto {
    @Autowired
    private UserRepository userRepository;

    public LoanResponse mapToLoanResponse(List<Loan> loans){
        return new LoanResponse(loans);

    }
    public Loan mapToLoan(LoanRequest loanRequest) {
       Loan loan= new Loan();
        if (loan.getId()!=null) {
            loan.setId(loanRequest.getId());
        }
        loan.setApproved(loanRequest.isApproved());
        loan.setAmount(loanRequest.getAmount());

        return loan;
    }
}
