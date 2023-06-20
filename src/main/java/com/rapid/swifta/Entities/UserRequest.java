package com.rapid.swifta.Entities;

import com.rapid.swifta.Enums.EnumRequestType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column
    private String requestBody;

    @Column
    private final Date createdDate = new Date();

    @Column
    private Integer createdBy;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumRequestType requestType;


}
