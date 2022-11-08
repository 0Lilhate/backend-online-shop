package com.example.orderservice.service;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;
import com.example.orderservice.rest.dto.ClothOrderDto;
import com.example.orderservice.rest.dto.OrderDto;

public interface ConvertClothOrder {
    public Order dtoToOrder(OrderDto orderDto);
    public OrderDto orderToDto(Order order);
    ClothOrder dtoClothToClothOrder(ClothOrderDto clothOrderDto);
    ClothOrderDto clothOrderToDto(ClothOrder clothOrder);
}
