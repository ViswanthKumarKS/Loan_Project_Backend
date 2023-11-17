package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentType {

    @Id
    @GeneratedValue
    private Long id;

    private String typeName;

    @OneToMany(mappedBy = "documentType")
    private List<Document> documentList=new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;


}
