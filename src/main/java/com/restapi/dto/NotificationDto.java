package com.restapi.dto;

import com.restapi.model.Loan;
import com.restapi.model.Notification;
import com.restapi.request.LoanRequest;
import com.restapi.request.NotificationRequest;
import com.restapi.response.LoanResponse;
import com.restapi.response.NotificationResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationDto {

    public NotificationResponse mapToNotificationResponse(List<Notification> notifications){
        return new NotificationResponse(notifications);

    }
    public Notification mapToNotification(NotificationRequest notificationRequest) {
        Notification notification= new Notification();
        if (notification.getId()!=null) {
            notification.setId(Math.toIntExact(notificationRequest.getId()));
        }
        notification.setMessage(notificationRequest.getMessage());
        notification.setRecipient(notificationRequest.getRecipient());


        return notification;
    }
}
