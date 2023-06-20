package com.rapid.swifta.DTOs.RequestBodies;

import lombok.Data;

@Data
public class QueryAddress {

    private String country;
    private String state;
    private String area;
    private String street;
}
