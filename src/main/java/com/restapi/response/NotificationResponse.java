package com.restapi.response;

import com.restapi.model.Notification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NotificationResponse {
    private List<Notification> notifications;
}
