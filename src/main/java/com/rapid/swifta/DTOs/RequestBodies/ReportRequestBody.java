package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Enums.EnumReportType;
import lombok.Data;

@Data
public class ReportRequestBody {

    private Integer reportedUser;
    private Integer createdBy;
    private String reportBody;
    private EnumReportType enumReportType;

}
