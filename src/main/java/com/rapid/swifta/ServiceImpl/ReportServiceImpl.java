package com.rapid.swifta.ServiceImpl;

import com.rapid.swifta.Entities.Report;
import com.rapid.swifta.Repositories.ReportRepository;
import com.rapid.swifta.Services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Page<Report> getAllReport(Pageable pageable) {
        return null;
    }

    @Override
    public Report createReport(Report report) {
        return null;
    }

    @Override
    public void deleteReport(Integer reportId) {

    }

    @Override
    public Report getReportId(Integer reportId) {
        return null;
    }
}
