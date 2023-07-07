package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Enums.EnumPayment;
import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderBroadcastBody {

    private Integer clientId;
    private EnumPayment paymentMethod;
    private Address orderAddress;
    private List<Integer> serviceType;
    private Boolean isOneOff;
    private float quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
    private String orderDescription;
}
