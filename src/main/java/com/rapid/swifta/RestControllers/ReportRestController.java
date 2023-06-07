package com.rapid.swifta.RestControllers;

import com.rapid.swifta.Entities.Report;
import com.rapid.swifta.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/report")
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    public Page<Report> getAllReport(Pageable pageable){
        return reportService.getAllReport(pageable);
    }

    public Report getReportById(@RequestParam Integer reportId){
        return reportService.getReportId(reportId);
    }

    public void deleteReportById(@RequestBody Integer reportId){
        reportService.deleteReport(reportId);
    }

}
