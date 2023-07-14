package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Entities.Services;
import lombok.Data;

@Data
public class ServicesRequestBody {

    private Integer userId;
    private String serviceTitle;
    private String serviceSummary;
    private String serviceOffered;
    private Integer serviceTypeId;
    private float serviceCharge;

}
