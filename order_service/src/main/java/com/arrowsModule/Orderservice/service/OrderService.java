package com.arrowsModule.Orderservice.service;

import com.arrowsModule.Orderservice.dto.OrderLineItemDto;
import com.arrowsModule.Orderservice.dto.OrderRequest;
import com.arrowsModule.Orderservice.dto.OrderResponse;
import com.arrowsModule.Orderservice.model.Order;
import com.arrowsModule.Orderservice.model.OrderLineItems;
import com.arrowsModule.Orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDto()
                .stream()
                .map(this::mapToOrderLineItemModel).toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
        return "new order has placed.";
    }

    public List<OrderResponse> findAllOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderLineItems mapToOrderLineItemModel(OrderLineItemDto orderLineItemDto) {
        return OrderLineItems.builder()
                .id(orderLineItemDto.getId())
                .skuCode(orderLineItemDto.getSkuCode())
                .quantity(orderLineItemDto.getQuantity())
                .price(orderLineItemDto.getPrice())
                .build();
    }
    private OrderLineItemDto mapToOrderLineItemDto(OrderLineItems orderLineItems) {
        return OrderLineItemDto.builder()
                .id(orderLineItems.getId())
                .skuCode(orderLineItems.getSkuCode())
                .quantity(orderLineItems.getQuantity())
                .price(orderLineItems.getPrice())
                .build();
    }
    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItemsList(order.getOrderLineItemsList().stream()
                        .map(this::mapToOrderLineItemDto)
                        .toList())
                .build();
    }

}
