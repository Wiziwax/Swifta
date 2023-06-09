package com.rapid.swifta.ServiceImpl;


import com.rapid.swifta.DTOs.Responses.OrdersResponse;
import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.AddressRepository;
import com.rapid.swifta.Repositories.OrderDetailsRepository;
import com.rapid.swifta.Repositories.OrderRepository;
import com.rapid.swifta.Services.OrderService;
import com.rapid.swifta.Utils.UniqueNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Override
    public Orders createOrder(Orders order) {

        addressRepository.save(order.getOrderAddress());
        //Todo set the Payment aside so you can enable and disable at will
        order.setEnumOrderProgress(EnumOrderProgress.INITIATED);
        order.setDeliveryDate(order.getDeliveryDate());
        order.setDeliveryTime(order.getDeliveryTime());
        order.setPaymentMethod(order.getPaymentMethod());
        order.setMerchantId(order.getMerchantId());
        int uniqueNumber = generateUniqueNumber();
        order.setOrderNumber(uniqueNumber);

        while (isUnique(uniqueNumber)) {
            uniqueNumber = generateUniqueNumber();
            order.setOrderNumber(uniqueNumber);
        }


        orderDetailsRepository.save(order.getOrderDetails());
        return orderRepository.save(order);
    }

    @Override
    public Page<OrdersResponse> getAllOrders(Pageable pageable) {
        Page<Orders> allOrders = orderRepository.findAll(pageable);
        return dataTransferPage(allOrders);
    }

    @Override
    public Page<OrdersResponse> viewOrdersByMerchantId(Integer merchantId, Pageable pageable) {
        Page<Orders> ordersPage = orderRepository.findAllByMerchantId(pageable, merchantId);
        return dataTransferPage(ordersPage);
    }

    @Override
    public Page<OrdersResponse> viewOrdersByClientId(Integer clientId, Pageable pageable) {

        Page<Orders> ordersPage = orderRepository.findAllByClientId(clientId, pageable);
        return dataTransferPage(ordersPage);
    }

    @Override
    public Page<OrdersResponse> getAllClosedClient(Integer clientId, Pageable pageable) {
        Page<Orders> closedOrders = orderRepository.findByClientIdEqualsAndClosedIsTrue(clientId, pageable);
        return dataTransferPage(closedOrders);
    }

    @Override
    public Page<OrdersResponse> getAllClosedMerchant(Integer merchantId, Pageable pageable) {
        Page<Orders> closedOrdersMerchants = orderRepository.findByMerchantIdEqualsAndClosedIsTrue(merchantId, pageable);
        return dataTransferPage(closedOrdersMerchants);
    }

    @Override
    public Page<OrdersResponse> getAllOpenClient(Integer userId, Pageable pageable) {
        Page<Orders> ordersPage= orderRepository.findByClientIdEqualsAndClosedIsFalse(userId,  pageable);
        return dataTransferPage(ordersPage);
    }


    @Override
    public Page<OrdersResponse> getAllOpenMerchant(Integer userId, Pageable pageable) {
        Page<Orders> ordersPage= orderRepository.findByMerchantIdEqualsAndClosedIsFalse(userId,  pageable);
        return dataTransferPage(ordersPage);
    }

    @Override
    public EnumOrderProgress getOrderStatusClient(Orders order) {
        Orders existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow(()->new ResourceNotFoundException("Order with id " + order.getOrderId() + " not found"));
        return existingOrder.getEnumOrderProgress();
    }

    @Override
    public OrdersResponse getOrderById(Integer orderId) {
        Orders existingOrder = orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order with id " + orderId + " not found"));
        return dataTransfer(existingOrder);
    }

    @Override
    public OrdersResponse acceptOrderMerchant(Orders order) {
        Orders existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow(()->
                new ResourceNotFoundException("Order with id " + order.getOrderId() + (" was not found")));

        existingOrder.setHasMerchantAccepted(!existingOrder.isHasMerchantAccepted());
        existingOrder.setEnumOrderProgress(EnumOrderProgress.IN_PROGRESS);
        orderRepository.save(existingOrder);
        return dataTransfer(existingOrder);
    }

    @Override
    public void deleteByIdClient(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrdersResponse editOrderClient(Orders orders) {
        Orders existingOrder = orderRepository.findById(orders.getOrderId()).orElseThrow(()->
                new ResourceNotFoundException("Order with id " + orders.getOrderId() + (" was not found")));
        existingOrder.setDeliveryTime(orders.getDeliveryTime());
        return dataTransfer(existingOrder);
    }


    public Page<OrdersResponse> dataTransferPage(Page<Orders> ordersPage){

        return ordersPage.map(orders -> OrdersResponse.builder()
                .clientId(orders.getClientId())
                .orderAddress(orders.getOrderAddress())
                .enumOrderProgress(orders.getEnumOrderProgress())
                .orderDate(orders.getOrderDate())
                .deliveryDate(orders.getDeliveryDate())
                .orderTime(orders.getOrderTime())
                .deliveryTime(orders.getDeliveryTime())
                .paymentMethod(orders.getPaymentMethod())
                .closed(orders.isClosed())
                .isOneOff(orders.isOneOff())
                .merchantId(orders.getMerchantId())
                .hasMerchantAccepted(orders.isHasMerchantAccepted())
                .orderDetails(orders.getOrderDetails())
                .orderComment(orders.getOrderComment())
                .build());
    }


    public OrdersResponse dataTransfer(Orders existingOrder){
        return OrdersResponse.builder()
                .clientId(existingOrder.getClientId())
                .orderAddress(existingOrder.getOrderAddress())
                .enumOrderProgress(existingOrder.getEnumOrderProgress())
                .orderDate(existingOrder.getOrderDate())
                .deliveryDate(existingOrder.getDeliveryDate())
                .orderTime(existingOrder.getOrderTime())
                .deliveryTime(existingOrder.getDeliveryTime())
                .paymentMethod(existingOrder.getPaymentMethod())
                .isOneOff(existingOrder.isOneOff())
                .closed(existingOrder.isClosed())
                .merchantId(existingOrder.getMerchantId())
                .hasMerchantAccepted(existingOrder.isHasMerchantAccepted())
                .orderDetails(existingOrder.getOrderDetails())
                .orderComment(existingOrder.getOrderComment()).build();

    }

    private int generateUniqueNumber() {
       return UniqueNumberGenerator.generateUniqueNumberWithDigits(10);
    }

    private boolean isUnique(int uniqueNumber){
        return orderRepository.existsByOrderNumber(uniqueNumber);
    }
}

