package com.restapi.response;

import com.restapi.model.ViewStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ViewStatusResponse {

    private List<ViewStatus> viewStatusList;
}
