package com.rapid.swifta.DTOs;


import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.UserProps.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserModel {

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
    private long jobCount;
    private Role role;
    private Address address;
    private List<Integer> serviceType;

}
