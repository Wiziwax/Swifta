package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table
@Entity
@Builder
@AllArgsConstructor
public class Services{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer servicesId;

    @Column
    private Integer userId;

    @Column
    private String serviceTitle;

    @Column
    private String serviceSummary;

    @Column
    private String servicesOffered;

    @Column
    private Integer serviceTypeId;

    @Column
    private int rateCount;

    @Column
    private float serviceRating;

    @Column
    private int jobsCompleted;

    @Column
    private float serviceCharge;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST},  fetch = LAZY)
    @JoinColumn(name = "service_attachment_id")
    @ToString.Exclude
    private Attachment portfolios;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST},  fetch = LAZY)
    @JoinColumn(name = "service_comment_id")
    @ToString.Exclude
    private Comments serviceComments;

}
