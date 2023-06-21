package com.rapid.swifta.RestControllers;


import com.rapid.swifta.DTOs.Responses.OrdersResponse;
import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.InnerService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order/merchant")
public class MerchantOrderRestController {


    @Autowired
    private OrderService orderService;

    @GetMapping("viewallorders")
    public Page<OrdersResponse> viewAllOrders(@RequestParam Integer merchantId, Pageable pageable){
        return orderService.viewOrdersByMerchantId(merchantId, pageable);
    }

    @GetMapping("viewallopenorders")
    public Page<OrdersResponse> getAllOpenOrders(@RequestParam Integer merchantId, Pageable pageable){
        return orderService.getAllOpenMerchant(merchantId, pageable);
    }

    @GetMapping("viewallclosedorders")
    public Page<OrdersResponse> getAllClosedOrders(@RequestParam Integer merchantId, Pageable pageable){
        return orderService.getAllClosedMerchant(merchantId, pageable);
    }

    @PutMapping("acceptorder")
    public OrdersResponse acceptOrderMerchant(@RequestBody Orders orders){
        return orderService.acceptOrderMerchant(orders);
    }


}
