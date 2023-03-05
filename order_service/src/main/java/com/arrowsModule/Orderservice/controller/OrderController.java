package com.arrowsModule.Orderservice.controller;

import com.arrowsModule.Orderservice.dto.OrderRequest;
import com.arrowsModule.Orderservice.dto.OrderResponse;
import com.arrowsModule.Orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> findAllOrders(){
        return orderService.findAllOrders();
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest,RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()->"Ops! inventory service is not available now");
    }
}
