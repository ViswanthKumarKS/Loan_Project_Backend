package com.restapi.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanRequest {
    @NotEmpty
    @Column(unique = false)
    private Long id;

    private boolean isApproved = false;


    private Long amount;
    private Long user_id;
}
