package com.restapi.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ViewStatusRequest {

    private Long id;

    private String message;
}
