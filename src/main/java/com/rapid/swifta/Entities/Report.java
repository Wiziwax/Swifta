package com.rapid.swifta.Entities;

import com.rapid.swifta.Enums.EnumReportType;
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
    private final Date createdDate= new Date();

    @Column
    private Integer reportedUser;

    @Column
    private String reportBody;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumReportType enumReportType;

    @Column
    private boolean treated;

    @Column
    private boolean ignored;
}
