package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer statsId;

    @Column
    private Integer noOfUsers;

    @Column
    private Integer noOfMerchants;

    @Column
    private Integer jobsCompleted;

    @Column
    private Integer trustedClients;



}
