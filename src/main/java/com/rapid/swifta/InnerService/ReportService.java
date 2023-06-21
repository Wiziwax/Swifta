package com.rapid.swifta.InnerService;

import com.rapid.swifta.DTOs.RequestBodies.ReportRequestBody;
import com.rapid.swifta.DTOs.Responses.ReportResponse;
import com.rapid.swifta.Entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    Page<ReportResponse> getAllReport(Pageable pageable);
    Report createReport(ReportRequestBody report);
    void deleteReport(Integer reportId);
    ReportResponse getReportId(Integer reportId);
    Page<ReportResponse> findByTreated(Pageable pageable, boolean isTreated);
    Page<ReportResponse> findByIgnored(Pageable pageable, boolean isIgnored);
}
