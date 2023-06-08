package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Enums.EnumRequestType;
import lombok.Builder;

import java.util.Date;

@Builder
public class UserRequestResponse {

    private String requestBody;
    private Date createdDate;
    private Integer createdBy;
    private EnumRequestType requestType;
}
