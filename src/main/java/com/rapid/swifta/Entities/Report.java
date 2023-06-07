package com.rapid.swifta.Entities;

import com.rapid.swifta.Enums.ReportType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer reportId;

    @Column
    private Integer createdBy;

    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    @Column
    private Integer reportedUser;

    @Column
    private String reportBody;

    @Column
    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Column
    private boolean isTreated;

    @Column
    private boolean isIgnored;
}
