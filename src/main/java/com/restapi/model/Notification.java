package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String message;

    private String recipient;

    @ManyToOne
    @JoinColumn(name="view_id",referencedColumnName = "id")
    private ViewStatus viewStatus;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private AppUser appUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Notification(String message) {
        this.message = message;
    }
}
