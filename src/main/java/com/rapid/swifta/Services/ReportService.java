package com.rapid.swifta.Services;

import com.rapid.swifta.Entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {

    Page<Report> getAllReport(Pageable pageable);
    Report createReport(Report report);
    void deleteReport(Integer reportId);
    Report getReportId(Integer reportId);
}
