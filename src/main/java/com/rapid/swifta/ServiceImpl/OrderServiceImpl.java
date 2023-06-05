package com.rapid.swifta.ServiceImpl;

import com.startup.rapidoil.Entities.Orders;
import com.startup.rapidoil.Enums.EnumOrderProgress;
import com.startup.rapidoil.Exceptions.ResourceNotFoundException;
import com.startup.rapidoil.Repositories.AddressRepository;
import com.startup.rapidoil.Repositories.OrderDetailsRepository;
import com.startup.rapidoil.Repositories.OrderRepository;
import com.startup.rapidoil.Services.OrderService;
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


//        orderDetailsRepository.save(order.getOrderDetails());
        return orderRepository.save(order);
    }

    @Override
    public Page<Orders> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Orders> viewOrdersByMerchantId(Integer merchantId, Pageable pageable) {
        return orderRepository.findAllByMerchantId(pageable, merchantId);
    }

    @Override
    public Page<Orders> viewOrdersByClientId(Integer clientId, Pageable pageable) {
        return orderRepository.findAllByClientId(clientId, pageable);
    }

    @Override
    public Page<Orders> getAllClosed(Integer userId, Pageable pageable) {
        return orderRepository.findByClientIdEqualsAndClosedIsTrue(userId, pageable);
    }

    @Override
    public Page<Orders> getAllClosedMerchant(Integer merchantId, Pageable pageable) {
        return orderRepository.findByMerchantIdEqualsAndClosedIsTrue(merchantId, pageable);
    }

    @Override
    public Page<Orders> getAllOpen(Integer userId, Pageable pageable) {
        return orderRepository.findByClientIdEqualsAndClosedIsFalse(userId,  pageable);
    }

    @Override
    public EnumOrderProgress getOrderStatusClient(Orders order) {
        Orders existingOrder = getOrderById(order.getOrderId());
        return existingOrder.getEnumOrderProgress();
    }

    @Override
    public Orders getOrderById(Integer orderId) {
        return orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order with id " + orderId + " not found"));
    }

    @Override
    public Orders acceptOrderMerchant(Orders order) {
        Orders existingOrder = getOrderById(order.getOrderId());
        existingOrder.setHasMerchantAccepted(!existingOrder.isHasMerchantAccepted());
        orderRepository.save(existingOrder);
        return existingOrder;
    }

    @Override
    public void deleteByIdClient(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Orders editOrderClient(Orders orders) {
        Orders existingOrder = getOrderById(orders.getOrderId());
        existingOrder.setDeliveryTime(orders.getDeliveryTime());
        return existingOrder;
    }
}

