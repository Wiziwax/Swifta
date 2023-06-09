package com.rapid.swifta.Repositories;

import com.rapid.swifta.Entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

    Page<Report> findAllByTreated(boolean isTreated, Pageable pageable);

    Page<Report> findAllByIgnored(boolean isIgnored, Pageable pageable);

}
