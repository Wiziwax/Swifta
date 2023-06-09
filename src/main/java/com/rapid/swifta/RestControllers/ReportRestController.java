package com.rapid.swifta.RestControllers;

import com.rapid.swifta.DTOs.RequestBodies.GenericRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.ReportRequestBody;
import com.rapid.swifta.DTOs.RequestBodies.ReportUpdateBody;
import com.rapid.swifta.DTOs.Responses.ReportResponse;
import com.rapid.swifta.Entities.Report;
import com.rapid.swifta.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/report")
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("getall")
    public Page<ReportResponse> getAllReport(Pageable pageable){
        return reportService.getAllReport(pageable);
    }

    @GetMapping("getbyid")
    public ReportResponse getReportById(@RequestParam Integer reportId){
        return reportService.getReportId(reportId);
    }

    @DeleteMapping("delete")
    public void deleteReportById(@RequestBody GenericRequestBody requestBody){
        reportService.deleteReport(requestBody.getId());
    }

    @PostMapping("create")
    public Report createReport(@RequestBody ReportRequestBody reportRequestBody){
        return reportService.createReport(reportRequestBody);
    }

    @GetMapping("getignored")
    public Page<ReportResponse> getIgnored(@RequestBody ReportUpdateBody reportUpdateBody, Pageable pageable){
        return reportService.findByIgnored(pageable, reportUpdateBody.isIgnored());
    }

    @GetMapping("gettreated")
    public Page<ReportResponse> getTreated(@RequestBody ReportUpdateBody reportUpdateBody, Pageable pageable){
        return reportService.findByTreated(pageable, reportUpdateBody.isTreated());
    }
}
