package com.restapi.service;

import com.restapi.dto.NotificationDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Notification;
import com.restapi.repository.NotificationRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.NotificationRequest;
import com.restapi.response.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationDto notificationDto;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    public NotificationResponse findall() {
        List<Notification> notificationList=notificationRepository.findAll();

        return notificationDto.mapToNotificationResponse(notificationList);
    }


    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {
        Notification notification = notificationDto.mapToNotification(notificationRequest);

        AppUser appUser=userRepository.findById(notificationRequest.getUser_id())
                .orElseThrow(()-> new ResourceNotFoundException("user_id","user_id",notificationRequest.getUser_id()));
        notification.setAppUser(appUser);


        notificationRepository.save(notification);
        return findall();
    }
}
