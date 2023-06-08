package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Enums.EnumRequestType;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class UserRequestRequestBody {

    private String requestBody;
    private Integer createdBy;
    private EnumRequestType requestType;
}
