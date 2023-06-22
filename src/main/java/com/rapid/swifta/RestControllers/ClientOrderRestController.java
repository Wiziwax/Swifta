package com.rapid.swifta.RestControllers;


import com.rapid.swifta.DTOs.RequestBodies.OrderBroadcastBody;
import com.rapid.swifta.DTOs.RequestBodies.OrdersRequestBody;
import com.rapid.swifta.DTOs.Responses.OrdersResponse;
import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Enums.EnumOrderProgress;
import com.rapid.swifta.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order/client")
public class ClientOrderRestController {

    @Autowired
    private OrderService orderService;

//  Client uses this API to create an order
    @PostMapping("createorder")
    public Orders createOrder(@RequestBody OrdersRequestBody orders){
        return orderService.createOrder(orders);
    }

//    API to view all created orders
    @GetMapping("viewallcreatedorders")
    public Page<OrdersResponse> viewAllOrders(@RequestParam Integer clientId, Pageable pageable){
        return orderService.viewOrdersByClientId(clientId, pageable);
    //TODO THIS CLIENT ID HERE IS SUPPOSED TO COME FROM THE SIGNED IN USER'S ID, SO ONCE SECURITY IS COMPLETED, IT SHOULDN'T COME FROM THE FRONTEND AGAIN
    }


//    View all orders the user is working on
    @GetMapping("getallopenorders")
    public Page<OrdersResponse> getAllOpenOrders(@RequestParam Integer userId, Pageable pageable){
        return orderService.getAllOpenClient(userId, pageable);
    }

//    View all finished orders
    @GetMapping("getallclosedorders")
    public Page<OrdersResponse> getAllClosedOrders(@RequestParam Integer userId, Pageable pageable){
        return orderService.getAllClosedClient(userId, pageable);
    }

//    Track order
    @GetMapping("getstatus")
    public EnumOrderProgress getOrderStatus(@RequestBody Orders order){
        return orderService.getOrderStatusClient(order);
    }

//    Delete Order
    @DeleteMapping("deleteorderbyid")
    public void deleteOrderById(Integer orderId){
        orderService.deleteByIdClient(orderId);
    }

//    Edit order
    @PutMapping("editorder")
    OrdersResponse editOrder(@RequestBody Orders orders){
       return orderService.editOrderClient(orders);
    }

//    Create order broadcast
    @PostMapping("orderbroadcast")
    public String createBroadcast(@RequestParam(required = false) String country,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String streetName,
            @RequestParam(required = false) Integer serviceType,
                                  @RequestBody OrderBroadcastBody orderBroadcastBody, Pageable pageable){
        return orderService.createBroadcast(country,state,area,streetName,serviceType,orderBroadcastBody, pageable);
    }

}
