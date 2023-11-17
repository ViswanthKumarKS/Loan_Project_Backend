package com.restapi.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentRequest {
    private Long id;

    private String document_name;





    private byte[] photo;

    private Integer user_id;

    private Long type_id;
}
