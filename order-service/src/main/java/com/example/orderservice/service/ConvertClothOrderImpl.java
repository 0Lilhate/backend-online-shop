package com.example.orderservice.service;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;
import com.example.orderservice.rest.dto.ClothOrderDto;
import com.example.orderservice.rest.dto.OrderDto;
import org.springframework.stereotype.Service;


import java.util.stream.Collectors;

@Service
public class ConvertClothOrderImpl implements ConvertClothOrder{


    @Override
    public Order dtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setEmailUser(orderDto.getEmailUser());



        order.setClothOrders(orderDto.getClothOrderDtoList().stream()
                .map(this::dtoClothToClothOrder).collect(Collectors.toList()));


        return order;
    }

    @Override
    public OrderDto orderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setAddress(order.getAddress());
        orderDto.setEmailUser(order.getEmailUser());


        orderDto.setClothOrderDtoList(order.getClothOrders().stream()
                .map(this::clothOrderToDto).collect(Collectors.toList()));



        return orderDto;
    }

    @Override
    public ClothOrder dtoClothToClothOrder(ClothOrderDto clothOrderDto){
        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(clothOrderDto.getPrice());
        clothOrder.setQuantity(clothOrderDto.getQuantity());
        String skuCode = clothOrderDto.getName()+"_"+ clothOrderDto.getColor()+ "_" + clothOrderDto.getSize();
        clothOrder.setSkuCode(skuCode);
        return clothOrder;
    }
    @Override
    public ClothOrderDto clothOrderToDto(ClothOrder clothOrder){

        ClothOrderDto clothOrderDto = new ClothOrderDto();

        clothOrderDto.setPrice(clothOrder.getPrice());
        clothOrderDto.setQuantity(clothOrder.getQuantity());

        String [] arrString = clothOrder.getSkuCode().split("_");
        clothOrderDto.setName(arrString[0]);
        clothOrderDto.setColor(arrString[1]);
        clothOrderDto.setSize(arrString[2]);

        return clothOrderDto;
    }
}
