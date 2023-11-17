package com.restapi.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentTypeRequest {
    private Long id;

    private String typeName;

}
