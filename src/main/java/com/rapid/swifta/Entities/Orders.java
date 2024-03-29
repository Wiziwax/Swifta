package com.rapid.swifta.Entities;


import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Enums.EnumPayment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalTime;
import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;
import static java.time.LocalTime.now;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    @Column
    private Integer clientId;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST})
    @JoinColumn(name = "order_address_id")
    private Address orderAddress;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumOrderProgress enumOrderProgress;

    @Column
    @CreatedDate
    private final Date orderDate = new Date();

    @Column
    private Date deliveryDate;

    @Column
    private final LocalTime orderTime = now();

    @Column
    private LocalTime deliveryTime;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumPayment paymentMethod;

    @Column
    private boolean closed;

    @Column
    private boolean isOneOff;

    //Todo Make it possible to request multiple
    @Column
    private Integer merchantId;

    private boolean hasMerchantAccepted;

    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST},  fetch = LAZY)
    @JoinColumn(name = "order_details_id")
    private OrderDetails orderDetails ;

    @Column
    private String orderComment;

    @Column
    private Integer orderNumber;
}
