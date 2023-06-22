package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.UserProps.Role;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class UserResponse {

    private Integer userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private Date createdDate;
    private String mobile;
    private String image;
    private String address;
    private List<String> serviceType;
    private BigDecimal serviceWorth;
    private String jobDescription;
    private Role role;
    private float rating;
    private int jobCount;
    private boolean isFavourite;
    private int rateCount;
    private boolean isBusy;
    private boolean verified;
}
