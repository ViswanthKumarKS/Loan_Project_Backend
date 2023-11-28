package com.restapi.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentTypeRequest {
    private Long id;

    @NotBlank(message = "Document name cannot be blank")
    private String documentName;

    @NotNull(message = "User ID cannot be null")
    private Long user_id;

    private boolean isBoolean = false;

    private String documentFile;

}
