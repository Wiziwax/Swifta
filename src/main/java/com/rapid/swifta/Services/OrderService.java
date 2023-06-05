package com.rapid.swifta.Services;


import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Enums.EnumOrderProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Orders createOrder(Orders order);
    Page<Orders> getAllOrders(Pageable pageable);
    Page<Orders> viewOrdersByMerchantId(Integer merchantId, Pageable pageable);
    Page<Orders> viewOrdersByClientId(Integer clientId, Pageable pageable);
    Page<Orders> getAllClosed(Integer userId, Pageable pageable);
    Page<Orders> getAllClosedMerchant(Integer merchantId, Pageable pageable);
    Page<Orders> getAllOpen(Integer userId, Pageable pageable);
    EnumOrderProgress getOrderStatusClient(Orders orders);
    Orders getOrderById(Integer orderId);
    Orders acceptOrderMerchant(Orders order);
    void deleteByIdClient(Integer orderId);
    Orders editOrderClient(Orders orders);

}
