package com.restaurant.dianca.controller;

import com.restaurant.dianca.dto.OrderSubmitDTO;
import com.restaurant.dianca.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/submit")
    public String submitOrder(@RequestBody OrderSubmitDTO orderDto) {
        return orderService.submitOrder(orderDto);
    }
}
