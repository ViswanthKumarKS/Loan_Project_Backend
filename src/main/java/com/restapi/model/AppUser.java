package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users") // don't use User
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;


    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role roles;

    @OneToMany(mappedBy = "appUser")
    private List<Loan> loan=new ArrayList<>();


    @OneToOne(mappedBy="appUser")
    private Account accountList;

    @OneToMany(mappedBy = "appUser")
    private List<Notification> notificationList;

    @OneToMany(mappedBy="appUser")
    private List<Document> documentList;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
