package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ViewStatus {
    @Id
    @GeneratedValue
    private Long id;


    @Column(nullable = false)
    private String message;

    @OneToMany(mappedBy = "viewStatus")
    private List<Notification> notification=new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ViewStatus(String message) {

        this.message = message;

    }
}
