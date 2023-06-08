package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Enums.EnumPayment;
import lombok.Data;

@Data
public class OrdersRequestBody {

    private Integer orderId;
    private Integer clientId;
    private AddressRequestBody orderAddress;
    private EnumPayment paymentMethod;
    private boolean isOneOff;
    private Integer merchantId;
    private OrderDetailsRequestBody orderDetails;
    private String orderComment;

}
