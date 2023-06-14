package com.rapid.swifta.RestControllers;


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

    @PostMapping("createorder")
    public Orders createOrder(@RequestBody OrdersRequestBody orders){
        return orderService.createOrder(orders);
    }

    @GetMapping("viewallcreatedorders")
    //TODO THIS CLIENT ID HERE IS SUPPOSED TO COME FROM THE SIGNED IN USER'S ID, SO ONCE SECURITY IS COMPLETED, IT SHOULDN'T COME FROM THE FRONTEND AGAIN
    public Page<OrdersResponse> viewAllOrders(@RequestParam Integer clientId, Pageable pageable){
        return orderService.viewOrdersByClientId(clientId, pageable);
    }

    @GetMapping("getallopenorders")
    public Page<OrdersResponse> getAllOpenOrders(@RequestParam Integer userId, Pageable pageable){
        return orderService.getAllOpenClient(userId, pageable);
    }

    @GetMapping("getallclosedorders")
    public Page<OrdersResponse> getAllClosedOrders(@RequestParam Integer userId, Pageable pageable){
        return orderService.getAllClosedClient(userId, pageable);
    }

    @GetMapping("getstatus")
    public EnumOrderProgress getOrderStatus(@RequestBody Orders order){
        return orderService.getOrderStatusClient(order);
    }

    @DeleteMapping("deleteorderbyid")
    public void deleteOrderById(Integer orderId){
        orderService.deleteByIdClient(orderId);
    }

    @PutMapping("editorder")
    OrdersResponse editOrder(@RequestBody Orders orders){
       return orderService.editOrderClient(orders);
    }


}
