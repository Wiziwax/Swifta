package com.rapid.swifta.Services;


import com.rapid.swifta.DTOs.RequestBodies.OrdersRequestBody;
import com.rapid.swifta.DTOs.Responses.OrdersResponse;
import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Enums.EnumOrderProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Orders createOrder(OrdersRequestBody order);
    Page<OrdersResponse> getAllOrders(Pageable pageable);
    Page<OrdersResponse> viewOrdersByMerchantId(Integer merchantId, Pageable pageable);
    Page<OrdersResponse> viewOrdersByClientId(Integer clientId, Pageable pageable);
    Page<OrdersResponse> getAllClosedClient(Integer clientId, Pageable pageable);
    Page<OrdersResponse> getAllClosedMerchant(Integer merchantId, Pageable pageable);
    Page<OrdersResponse> getAllOpenClient(Integer userId, Pageable pageable);
    Page<OrdersResponse> getAllOpenMerchant(Integer userId, Pageable pageable);
    EnumOrderProgress getOrderStatusClient(Orders orders);
    OrdersResponse getOrderById(Integer orderId);
    OrdersResponse acceptOrderMerchant(Orders order);
    void deleteByIdClient(Integer orderId);
    OrdersResponse editOrderClient(Orders orders);

}
