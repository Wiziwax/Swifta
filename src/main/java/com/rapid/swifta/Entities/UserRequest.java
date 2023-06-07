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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer requestId;

    @Column
    private String requestBody;

    @Column
    private Date createdDate;

    @Column
    private Integer createdBy;

    @Column
    private EnumRequestType requestType;


}
