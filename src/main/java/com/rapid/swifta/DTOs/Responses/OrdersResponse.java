package com.rapid.swifta.DTOs.Responses;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Enums.EnumPayment;
import lombok.Builder;
import java.time.LocalTime;
import java.util.Date;
import static java.time.LocalTime.now;

@Builder
public class OrdersResponse {

    private Integer clientId;
    private Address orderAddress;
    private EnumOrderProgress enumOrderProgress;
    private final Date orderDate = new Date();
    private Date deliveryDate;
    private final LocalTime orderTime = now();
    private LocalTime deliveryTime;
    private EnumPayment paymentMethod;
    private boolean closed;
    private boolean isOneOff;
    private Integer merchantId;
    private boolean hasMerchantAccepted;
    private OrderDetailResponse orderDetails;
    private String orderComment;

}
