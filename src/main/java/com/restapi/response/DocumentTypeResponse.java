package com.restapi.response;

import com.restapi.model.DocumentType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class DocumentTypeResponse extends DocumentType {


    private Long id;

    private String documentName;

    private Long user_id;
    private String username;

    private boolean isBoolean=false;
    private String documentFile;
}
