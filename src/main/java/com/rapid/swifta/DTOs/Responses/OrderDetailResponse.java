package com.rapid.swifta.DTOs.Responses;

import jakarta.persistence.Column;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class OrderDetailResponse {

    private Integer orderId;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
}
