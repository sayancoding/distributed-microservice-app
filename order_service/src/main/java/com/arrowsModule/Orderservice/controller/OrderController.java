package com.arrowsModule.Orderservice.controller;

import com.arrowsModule.Orderservice.dto.OrderRequest;
import com.arrowsModule.Orderservice.dto.OrderResponse;
import com.arrowsModule.Orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> findAllOrders(){
        return orderService.findAllOrders();
    }

}
