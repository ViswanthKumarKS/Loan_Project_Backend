package com.restapi.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotificationRequest {

    private Long id;

    private String message;

    private String recipient;

    private Long user_id;

}
