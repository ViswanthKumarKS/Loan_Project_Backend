package com.restapi.service;

import com.restapi.dto.LoanDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.model.Loan;
import com.restapi.repository.LoanRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoanApprovalRequest;
import com.restapi.request.LoanRequest;
import com.restapi.response.AccountResponse;
import com.restapi.response.LoanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {


    @Autowired
    private LoanDto loanDto;
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;



    public List<LoanResponse> findall() {
        List<Loan> loans= loanRepository.findAll();
        return loanDto.mapToLoanResponse(loans);

    }

    public List<LoanResponse> create(LoanRequest loanRequest) {
        Loan loan = loanDto.mapToLoan(loanRequest);
        AppUser appUser=userRepository.findById(Long.valueOf(Math.toIntExact(loanRequest.getUser_id())))
                .orElseThrow(()-> new ResourceNotFoundException("user_id","user_id",loanRequest.getUser_id()));
        loan.setAppUser(appUser);
        loanRepository.save(loan);
        return findall();

    }

    public List<LoanResponse> notApproval() {

        List<Loan> loan=loanRepository.findNotApproval();

        return loanDto.mapToLoanResponse(loan);
    }

    public String approval(Long id) {
        loanRepository.updateUser(id);
        return "success";
    }

    public LoanResponse findById(Long userId) {
        Loan loan = loanRepository.findByUserId(userId).
                orElseThrow((() -> new ResourceNotFoundException("AppUserId",
                        "AppUserId", userId)));
        LoanResponse loanResponse = loanDto.mapToLoanResponse(loan);
        return loanResponse;
    }
}
