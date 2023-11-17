package com.restapi.response;


import com.restapi.model.Loan;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LoanResponse {

    private List<Loan> loans;
}
