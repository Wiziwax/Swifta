package com.rapid.swifta.DTOs.RequestBodies;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AddressRequestBody {

    private String houseNumber;
    private String streetName;
    private String area;
    private String state;
    private String country;
    private Float latitude;
    private Float longitude;
    private String zipCode;
    private Float altitude;

}
