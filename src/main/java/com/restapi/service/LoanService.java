package com.restapi.service;

import com.restapi.dto.LoanDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Account;
import com.restapi.model.AppUser;
import com.restapi.model.Loan;
import com.restapi.repository.LoanRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoanRequest;
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



    public LoanResponse findall() {
        List<Loan> loanList = loanRepository.findAll();
        return loanDto.mapToLoanResponse(loanList);

    }

    public LoanResponse create(LoanRequest loanRequest) {
        Loan loanResponse=loanDto.mapToLoan(loanRequest);
        AppUser appUser=userRepository.findById(Math.toIntExact(loanRequest.getUser_id()))
                .orElseThrow(()-> new ResourceNotFoundException("user_id","user_id",loanRequest.getUser_id()));
        loanResponse.setAppUser(appUser);
        loanRepository.save(loanResponse);
        return findall();

    }
}
