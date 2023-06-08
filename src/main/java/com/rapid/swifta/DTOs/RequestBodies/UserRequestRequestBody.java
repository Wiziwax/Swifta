package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Enums.EnumRequestType;
import lombok.Data;

@Data
public class UserRequestRequestBody {

    private String requestBody;
    private Integer createdBy;
    private EnumRequestType requestType;
}
