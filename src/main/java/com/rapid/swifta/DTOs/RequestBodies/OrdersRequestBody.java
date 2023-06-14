package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.OrderDetails;
import com.rapid.swifta.Enums.EnumPayment;
import lombok.Data;

@Data
public class OrdersRequestBody {

    private Integer orderId;
    private Integer clientId;
    private Integer merchantId;
    private EnumPayment paymentMethod;
    private boolean isOneOff;
    private Address orderAddress;
    private OrderDetails orderDetails;
    private String orderComment;

}
