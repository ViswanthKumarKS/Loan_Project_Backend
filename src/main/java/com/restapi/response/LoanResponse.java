package com.restapi.response;


import com.restapi.model.Loan;
import com.restapi.request.LoanRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LoanResponse {

    private List<Loan> loans  = new ArrayList<>();

    private Long id;

    private Long amount;
    private String username;
    private String LoanType;
    private boolean isApproved = false;



}
