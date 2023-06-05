package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer addressId;
    @Column
    private String houseNumber;
    @Column
    private String streetName;
    @Column
    private String area;
    @Column
    private String state;
    @Column
    private String country;
    @Column
    private Float latitude;
    @Column
    private Float longitude;
    @Column
    private String zipCode;
    @Column
    private Float altitude;
    @Column
    private Integer userId;
}
