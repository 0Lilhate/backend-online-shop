package com.example.orderservice.service;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;
import com.example.orderservice.repository.ClotheOrderRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.rest.dto.ClothOrderDto;
import com.example.orderservice.rest.dto.OrderDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@Import(OrderServiceImpl.class)
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private ClotheOrderRepository clotheOrderRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void placeOrderWhenTheObjectWasNotFound() {
        val order1 = new Order();
        order1.setAddress("Москва");
        order1.setEmailUser("daniil");
        order1.setId(1L);
        order1.setOrderNumber(UUID.randomUUID().toString());
        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");
        ClothOrder clothOrder1 = new ClothOrder();
        clothOrder1.setPrice(1999L);
        clothOrder1.setQuantity(4);
        clothOrder1.setSkuCode("clothe2_black_XL");
        order1.setClothOrders(List.of(clothOrder1, clothOrder));

        when(orderRepository.findByOrderNumber(any())).thenReturn(Optional.of(order1));
        when(orderRepository.save(any())).thenReturn(order1);

        assertThat(orderService.placeOrder(order1).getOrderNumber()).isEqualTo(order1.getOrderNumber());

    }


    @Test
    void addNewClothe() {

        val order1 = new Order();
        order1.setAddress("Москва");
        order1.setEmailUser("daniil");
        order1.setId(1L);
        order1.setOrderNumber(UUID.randomUUID().toString());
        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");
        ArrayList<ClothOrder>  clothOrders = new ArrayList<>();
        clothOrders.add(clothOrder);
        order1.setClothOrders(clothOrders);

        when(orderRepository.findByEmailUser(order1.getEmailUser())).thenReturn(Optional.of(order1));

        ClothOrder clothOrder1 = new ClothOrder();
        clothOrder1.setPrice(1999L);
        clothOrder1.setQuantity(4);
        clothOrder1.setSkuCode("clothe2_black_XL");

        val dto = new OrderDto();
        dto.getClothOrderDtoList().add(new ClothOrderDto());

        when(orderRepository.save(order1)).thenReturn(order1);

        order1.getClothOrders().add(clothOrder1);
        val expected = orderService.addNewClothe(order1.getEmailUser(), clothOrder1);

        assertThat(expected).isEqualTo(order1);
    }


}