package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String State;

    private String city;

    @Column(nullable = false, unique = true, length = 200)
    private Long acc_no;

    @Column(nullable = false)

    private double balance;
    @JsonIgnore

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser appUser;


//    @CreationTimestamp
//    @Column(updatable = false)
//    private LocalDateTime createdAt;


}
