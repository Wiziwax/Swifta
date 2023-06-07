package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.ServiceType;
import com.rapid.swifta.UserProps.Role;
import lombok.Builder;
import java.util.Date;

@Builder
public class UserResponse {

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private Date createdDate;
    private String mobile;
    private String image;
    private Address address;
    private ServiceType serviceType;
    private Role role;
    private float rating;
    private int jobCount;
    private int rateCount;
    private boolean isBusy;
    private boolean verified;

}
