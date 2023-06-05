package com.rapid.swifta.RestControllers;


import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Services.OrderService;
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
    public Page<Orders> viewAllOrders(@RequestParam Integer merchantId, Pageable pageable){
        return orderService.viewOrdersByMerchantId(merchantId, pageable);
    }

    @GetMapping("viewallopenorders")
    public Page<Orders> getAllOpenOrders(@RequestParam Integer merchantId, Pageable pageable){
        return orderService.getAllOpen(merchantId, pageable);
    }

    @GetMapping("viewallclosedorders")
    public Page<Orders> getAllClosedOrders(@RequestParam Integer merchantId, Pageable pageable){
        return orderService.getAllClosedMerchant(merchantId, pageable);
    }

    @PutMapping("acceptorder")
    public Orders acceptOrderMerchant(@RequestBody Orders orders){
        return orderService.acceptOrderMerchant(orders);
    }


}
