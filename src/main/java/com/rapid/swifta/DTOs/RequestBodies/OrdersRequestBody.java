package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Enums.EnumPayment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdersRequestBody {

    private Integer orderId;
    private Integer clientId;
    private Integer merchantId;
    private EnumPayment paymentMethod;
    private String orderDescription;
    private boolean isOneOff;
    private Address orderAddress;
    private float quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
    private String orderComment;

}
