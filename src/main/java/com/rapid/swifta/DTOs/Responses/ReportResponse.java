package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Enums.EnumReportType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReportResponse {

    private User createdBy;
    private Date createdDate;
    private User reportedUser;
    private String reportBody;
    private EnumReportType enumReportType;
    private boolean isTreated;
    private boolean isIgnored;
}
