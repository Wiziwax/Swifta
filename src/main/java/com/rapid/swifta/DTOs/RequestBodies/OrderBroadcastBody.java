package com.rapid.swifta.DTOs.RequestBodies;

import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.OrderDetails;
import com.rapid.swifta.Enums.EnumPayment;
import lombok.Data;

import java.util.List;

@Data
public class OrderBroadcastBody {

    private Integer clientId;
    private EnumPayment paymentMethod;
    private Address orderAddress;
    private List<Integer> serviceType;
    private Boolean isOneOff;
    private OrderDetails orderDetails;
    private String orderDescription;
}
