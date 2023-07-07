package com.rapid.swifta.Entities;


import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Enums.EnumPayment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;
import static java.time.LocalTime.now;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table
@Entity
@Builder
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @OneToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST},  fetch = LAZY)
//    @JoinColumn(name = "order_details_id")
//    @ToString.Exclude
//    private OrderDetails orderDetails ;

    @Column
    private float quantity;

    @Column
    private BigDecimal pricePerUnit;

    @Column
    private BigDecimal totalPrice;


    @Column
    private String orderComment;

    @Column
    private Integer orderNumber;

    @Column
    private String orderDescription;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Orders orders = (Orders) o;
        return getOrderId() != null && Objects.equals(getOrderId(), orders.getOrderId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
