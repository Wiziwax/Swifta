package com.rapid.swifta.DTOs.RequestBodies;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderDetailsRequestBody {

    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;

}
