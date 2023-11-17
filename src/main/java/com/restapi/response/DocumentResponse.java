package com.restapi.response;

import com.restapi.model.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DocumentResponse {
    private Long id;

    private String document_name;



    private byte[] photo;

    private Integer user_id;

    private Integer type_id;


    public DocumentResponse(List<Document> documents) {
    }
}
