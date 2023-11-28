package com.restapi.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanRequest {

    @NotNull(message = "ID cannot be null")
    private Long id;

    private boolean isApproved = false;

    @NotNull(message = "Amount cannot be null")
    @Min(value = 1, message = "Amount must be greater than 0")
    private Long amount;

    @NotNull(message = "User ID cannot be null")
    private Long user_id;


    private String loanType;

}
