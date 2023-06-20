package com.rapid.swifta.ServiceImpl;

import com.rapid.swifta.DTOs.RequestBodies.OrderBroadcastBody;
import com.rapid.swifta.DTOs.RequestBodies.OrdersRequestBody;
import com.rapid.swifta.DTOs.Responses.OrdersResponse;
import com.rapid.swifta.Entities.Address;
import com.rapid.swifta.Entities.User;
import com.rapid.swifta.Entities.UserNotifications;
import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Exceptions.ResourceNotFoundException;
import com.rapid.swifta.Repositories.AddressRepository;
import com.rapid.swifta.Repositories.NotificationRepository;
import com.rapid.swifta.Repositories.OrderRepository;
import com.rapid.swifta.Repositories.UserRepository;
import com.rapid.swifta.Services.OrderService;
import com.rapid.swifta.Utils.NotificationBodyString;
import com.rapid.swifta.Utils.UniqueNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public Orders createOrder(OrdersRequestBody ordersRequestBody) {


        Orders order = new Orders();
//        addressRepository.save(order.getOrderAddress());
        //Todo set the Payment aside so you can enable and disable at will
        order.setEnumOrderProgress(EnumOrderProgress.INITIATED);
        order.setDeliveryDate(order.getDeliveryDate());
        order.setDeliveryTime(order.getDeliveryTime());
        order.setPaymentMethod(order.getPaymentMethod());
        order.setMerchantId(order.getMerchantId());

        UserNotifications userNotifications = new UserNotifications();
        dataTransferOrderRequest(order, ordersRequestBody, userNotifications);

        int uniqueNumber = generateUniqueNumber();
        order.setOrderNumber(uniqueNumber);
        userNotifications.setOrderNumber(uniqueNumber);

        while (isUnique(uniqueNumber)) {
            uniqueNumber = generateUniqueNumber();
            order.setOrderNumber(uniqueNumber);
            userNotifications.setOrderNumber(uniqueNumber);
        }

        notificationRepository.save(userNotifications);
//        orderDetailsRepository.save(order.getOrderDetails());
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

    @Override
    public String createBroadcast(String country, String state, String area, String streetName,
                                  Integer serviceType,
                                  OrderBroadcastBody orderBroadcastBody, Pageable pageable) {
        Orders orders = new Orders();
        orders.setClientId(orderBroadcastBody.getClientId());//TODO Change to Signed In User
        orders.setPaymentMethod(orderBroadcastBody.getPaymentMethod());
        orders.setOrderAddress(orderBroadcastBody.getOrderAddress());
        orders.setOneOff(orderBroadcastBody.getIsOneOff());
        orders.setOrderDetails(orderBroadcastBody.getOrderDetails());
        orders.setOrderDescription(orderBroadcastBody.getOrderDescription());

        orders.setEnumOrderProgress(EnumOrderProgress.INITIATED);
        orders.setDeliveryDate(orders.getDeliveryDate());
        orders.setDeliveryTime(orders.getDeliveryTime());
        orders.setPaymentMethod(orders.getPaymentMethod());
        orders.setMerchantId(orders.getMerchantId());




        int uniqueNumber = generateUniqueNumber();
        orders.setOrderNumber(uniqueNumber);

            while (isUnique(uniqueNumber)) {
                uniqueNumber = generateUniqueNumber();
                orders.setOrderNumber(uniqueNumber);
            }


        List<Address> addressList = addressRepository.findAllByQueryingColumns(
                country,
                state,
                area,
                streetName);
        List<Integer> addressIds = new ArrayList<>();
        for(Address address: addressList){
            addressIds.add(address.getAddressId());
        }
        List<Integer>testService= new ArrayList<>(Arrays.asList(1,2,3,4,107));
        List<User> broadcastUsers =
                userRepository.findListByServiceTypeAndLocation(addressIds,testService );

        for(User user: broadcastUsers){
            UserNotifications notifications = new UserNotifications();
            notifications.setCreatedBy(orderBroadcastBody.getClientId());//TODO Change to Signed In User
            notifications.setRead(false);
            notifications.setOrderNumber(uniqueNumber);
            notifications.setUserId(user.getUserId());
            notifications.setNotificationBody(
                    NotificationBodyString.NOTIFICATION_BODY(orderBroadcastBody.getClientId(),
                                                            orderBroadcastBody.getOrderDescription(),
                                                            uniqueNumber,
                                                            user.getUserId()));
            notifications.setOrderNumber(uniqueNumber);

            notificationRepository.save(notifications);
        }
        orderRepository.save(orders);

        return "Success";
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
                .orderDescription(orders.getOrderDescription())
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
                .orderDescription(existingOrder.getOrderDescription())
                .deliveryTime(existingOrder.getDeliveryTime())
                .paymentMethod(existingOrder.getPaymentMethod())
                .isOneOff(existingOrder.isOneOff())
                .closed(existingOrder.isClosed())
                .merchantId(existingOrder.getMerchantId())
                .hasMerchantAccepted(existingOrder.isHasMerchantAccepted())
                .orderDetails(existingOrder.getOrderDetails())
                .orderComment(existingOrder.getOrderComment()).build();

    }

    public void dataTransferOrderRequest(Orders orders, OrdersRequestBody ordersRequestBody, UserNotifications userNotifications){
        orders.setClientId(ordersRequestBody.getClientId());
        orders.setMerchantId(ordersRequestBody.getMerchantId());
        orders.setPaymentMethod(ordersRequestBody.getPaymentMethod());
        orders.setOneOff(ordersRequestBody.isOneOff());
        orders.setOrderDescription(orders.getOrderDescription());

        //////NOTIFICATIONS NOTIFICATIONS NOTIFICATIONS NOTIFICATIONS NOTIFICATIONS NOTIFICATIONS////////
        userNotifications.setUserId(ordersRequestBody.getMerchantId());//TODO Change to Signed In User
        userNotifications.setNotificationBody(NotificationBodyString.NOTIFICATION_BODY(
                ordersRequestBody.getClientId(),
                ordersRequestBody.getOrderDescription(),
                orders.getOrderNumber(),
                ordersRequestBody.getMerchantId()));
        userNotifications.setCreatedBy(ordersRequestBody.getClientId());
        //////////////////////////////////////////////////////////////////////////////////////////////

        orders.setOrderDetails(ordersRequestBody.getOrderDetails());
        orders.setOrderAddress(ordersRequestBody.getOrderAddress());
    }

    private int generateUniqueNumber() {
       return UniqueNumberGenerator.generateUniqueNumberWithDigits(10);
    }

    private boolean isUnique(int uniqueNumber){
        return orderRepository.existsByOrderNumber(uniqueNumber);
    }
}

