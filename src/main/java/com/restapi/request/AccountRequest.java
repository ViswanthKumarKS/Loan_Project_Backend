package com.restapi.request;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountRequest {
    @NotEmpty
    @Column(unique = false)
    private Integer id;

    @NotEmpty
    @Size(min=13,message = "acc_no should have 13 characters")
    private Long acc_no;

    @NotEmpty
    @Size(min=1000,message = "balance should have more than 1000 ruppes")
    private double balance;

    private Integer user_id;
}
