package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

@ToString
public class Loan {
    @Id
    @GeneratedValue
    private Long id;

    private boolean isApproved = false;

    private Long amount;

    private String LoanType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
