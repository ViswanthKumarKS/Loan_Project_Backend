package com.restapi.controller.Admin;

import com.restapi.request.NotificationRequest;
import com.restapi.response.NotificationResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.NotificationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/all")
    public ResponseEntity<APIResponse> getNotificationsByRecipient() {
       NotificationResponse notificationResponse=notificationService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(notificationResponse);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.sendNotification(notificationRequest);
        return ResponseEntity.ok("Notification sent successfully");
    }
}
