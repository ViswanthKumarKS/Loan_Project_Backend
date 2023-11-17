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
@Table(name="tab_document")
public class Document {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn(name="Type_id",referencedColumnName = "id")
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private AppUser appUser;

    @CreationTimestamp
    private LocalDateTime createdAt;





}
