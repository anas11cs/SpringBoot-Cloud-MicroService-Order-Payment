package com.tkxel.orderservice.controller;

import com.tkxel.orderservice.dto.TransactionRequest;
import com.tkxel.orderservice.dto.TransactionResponse;
import com.tkxel.orderservice.entity.Order;
import com.tkxel.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/bookorder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){
          return orderService.saveOrder(request);
    }

    @GetMapping("/findOrder/{orderId}")
    public Order findOrderById(@PathVariable Integer orderId){
        return orderService.findOrder(orderId);
    }

}
