package com.example.orderservice.service;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;

import java.util.List;

public interface OrderService {
    public Order placeOrder(Order order);
    Order addNewClothe(String email, ClothOrder clothOrder);
    Order removeClothe(String email, String skuCode);

    Order addOneQuantityInClothe(String email, String skuCode);

    List<Order> getAllOrder();

}
