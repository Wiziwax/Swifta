package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.UserProps.Role;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UserRequestBody {

    private Integer userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String mobile;
    private String image;
    private float rate;
    private float rating;
    private Role role;
    private Address address;
    private String jobDescription;
    private BigDecimal serviceWorth;
    private List<Integer> serviceType;


}
