package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Enums.EnumPayment;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
public class OrdersResponse {

    private Integer clientId;
    private Address orderAddress;
    private EnumOrderProgress enumOrderProgress;
    private Date orderDate;
    private Date deliveryDate;
    private LocalTime orderTime;
    private LocalTime deliveryTime;
    private EnumPayment paymentMethod;
    private boolean closed;
    private boolean isOneOff;
    private Integer merchantId;
    private boolean hasMerchantAccepted;
    private float quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
    private String orderComment;
    private String orderDescription;

}
