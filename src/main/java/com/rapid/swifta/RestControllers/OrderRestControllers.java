package com.rapid.swifta.RestControllers;


import com.rapid.swifta.Entities.Orders;
import com.rapid.swifta.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/order")
public class OrderRestControllers {

    @Autowired
    private OrderService orderService;

    @GetMapping("getallorders")
    Page<Orders> getAllUsers (Pageable pageable){
        return orderService.getAllOrders(pageable);
    }


}