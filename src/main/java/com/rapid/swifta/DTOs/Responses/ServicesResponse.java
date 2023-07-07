package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Entities.Attachment;
import com.rapid.swifta.Entities.Comments;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServicesResponse {

    private Integer servicesId;
    private Integer userId;
    private String serviceTitle;
    private String serviceSummary;
    private String serviceOffered;
    private Integer serviceTypeId;
    private int rateCount;
    private float serviceRating;
    private int jobsCompleted;
    private float serviceCharge;
    private Attachment portfolios;
    private Comments serviceComments;
}
