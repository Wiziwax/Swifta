package com.rapid.swifta.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_details_id;

    @Column
    private int quantity;

    @Column
    private BigDecimal pricePerUnit;

    @Column
    private BigDecimal totalPrice;


}
