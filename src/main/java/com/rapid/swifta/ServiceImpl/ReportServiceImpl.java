package com.rapid.swifta.ServiceImpl;

import com.rapid.swifta.DTOs.RequestBodies.ReportRequestBody;
import com.rapid.swifta.DTOs.Responses.ReportResponse;
import com.rapid.swifta.Entities.Report;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.ReportRepository;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Page<ReportResponse> getAllReport(Pageable pageable) {
        Page<Report> reportPage = reportRepository.findAll(pageable);
        return reportPageDataTransfer(reportPage);
    }

    @Override
    public Report createReport(ReportRequestBody report) {
        Report newReport = Report.builder()
                .reportedUser(report.getReportedUser())
                .reportBody(report.getReportBody())
                .createdBy(report.getCreatedBy())
                .enumReportType(report.getEnumReportType())
                .build();
        return reportRepository.save(newReport);
    }

    @Override
    public void deleteReport(Integer reportId) {
        reportRepository.deleteById(reportId);
    }

    @Override
    public ReportResponse getReportId(Integer reportId) {

        Report report = reportRepository.findById(reportId).orElseThrow(()->
                new ResourceNotFoundException("Report with ID " + reportId + " could not be found"));

        return ReportResponse.builder()
                .createdBy(userRepository.findById(report.getCreatedBy()).orElseThrow(()->
                        new ResourceNotFoundException("User with ID " + report.getCreatedBy() + " was not found")))
                .createdDate(report.getCreatedDate())
                .reportedUser(userRepository.findById(report.getReportedUser()).orElseThrow(()->
                        new ResourceNotFoundException("User with ID " + report.getReportedUser() + " was not found")))
                .enumReportType(report.getEnumReportType())
                .isTreated(report.isTreated())
                .isIgnored(report.isIgnored())
                .build();
    }

    @Override
    public Page<ReportResponse> findByTreated(Pageable pageable, boolean isTreated){
        Page<Report> reportPage = reportRepository.findAllByTreated(isTreated, pageable);
        return reportPageDataTransfer(reportPage);
    }

    @Override
    public Page<ReportResponse> findByIgnored(Pageable pageable, boolean isIgnored){
        Page<Report> ignoredPages = reportRepository.findAllByIgnored(isIgnored, pageable);
        return reportPageDataTransfer(ignoredPages);
    }


    Page<ReportResponse> reportPageDataTransfer(Page<Report> reportPage){
        return reportPage.<ReportResponse>map(report -> ReportResponse.builder()
                .createdBy(userRepository.findById(report.getCreatedBy()).orElseThrow(()->
                        new ResourceNotFoundException("User with ID " + report.getCreatedBy() + " was not found")))
                .createdDate(report.getCreatedDate())
                .reportedUser(userRepository.findById(report.getReportedUser()).orElseThrow(()->
                        new ResourceNotFoundException("User with ID " + report.getReportedUser() + " was not found")))
                .reportBody(report.getReportBody())
                .enumReportType(report.getEnumReportType())
                .isTreated(report.isTreated())
                .isIgnored(report.isIgnored())
                .build());
    }
}

