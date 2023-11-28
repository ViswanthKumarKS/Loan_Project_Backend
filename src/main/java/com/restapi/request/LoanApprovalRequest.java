package com.restapi.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanApprovalRequest {
    @NotEmpty
    @Column(unique = false)

    private Long id;
}
