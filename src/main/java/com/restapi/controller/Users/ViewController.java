package com.restapi.controller.Users;

import com.restapi.response.NotificationResponse;
import com.restapi.response.ViewStatusResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/view")
public class ViewController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private ViewService viewService;


    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllView() {
        ViewStatusResponse viewStatusResponse = viewService.findall();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(viewStatusResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    }
