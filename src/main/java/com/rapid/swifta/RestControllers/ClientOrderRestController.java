package com.rapid.swifta.RestControllers;


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
    public Orders createOrder(@RequestBody Orders orders){
        return orderService.createOrder(orders);
    }

    @GetMapping("viewallcreatedorders")
    public Page<Orders> viewAllOrders(@RequestParam Integer clientId, Pageable pageable){
        return orderService.viewOrdersByClientId(clientId, pageable);
    }

    @GetMapping("getallopenorders")
    public Page<Orders> getAllOpenOrders(@RequestParam Integer userId, Pageable pageable){
        return orderService.getAllOpen(userId, pageable);
    }

    @GetMapping("getallclosedorders")
    public Page<Orders> getAllClosedOrders(@RequestParam Integer userId, Pageable pageable){
        return orderService.getAllClosed(userId, pageable);
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
    Orders editOrder(@RequestBody Orders orders){
       return orderService.editOrderClient(orders);
    }


}
