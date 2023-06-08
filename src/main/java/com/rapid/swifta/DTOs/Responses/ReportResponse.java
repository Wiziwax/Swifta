package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Enums.EnumReportType;
import lombok.Builder;

import java.util.Date;

@Builder
public class ReportResponse {

    private Integer createdBy;
    private Date createdDate;
    private Integer reportedUser;
    private String reportBody;
    private EnumReportType enumReportType;
    private boolean isTreated;
    private boolean isIgnored;
}
