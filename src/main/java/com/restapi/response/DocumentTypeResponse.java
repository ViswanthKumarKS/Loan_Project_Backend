package com.restapi.response;

import com.restapi.model.Document;
import com.restapi.model.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@AllArgsConstructor
public class DocumentTypeResponse {
    private List<DocumentType> documentTypes;
}
