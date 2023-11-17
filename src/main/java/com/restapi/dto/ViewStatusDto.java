package com.restapi.dto;

import com.restapi.model.ViewStatus;
import com.restapi.request.ViewStatusRequest;
import com.restapi.response.ViewStatusResponse;

import java.util.List;

public class ViewStatusDto {
    public ViewStatusResponse mapToViewStatusResponse(List<ViewStatus> viewStatusList) {
        return new ViewStatusResponse(viewStatusList);

    }

    public ViewStatus mapToViewStatus(ViewStatusRequest viewStatusRequest) {
        ViewStatus viewStatus = new ViewStatus();
        if (viewStatus.getId() != null) {
            viewStatus.setId(viewStatusRequest.getId());
        }
        viewStatus.setMessage(viewStatusRequest.getMessage());

        return viewStatus;
    }
}