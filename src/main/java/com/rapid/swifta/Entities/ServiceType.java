package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer serviceTypeId;

    @Column
    private String serviceTypeName;

    @Column
    private String serviceTypeDescription;
}
