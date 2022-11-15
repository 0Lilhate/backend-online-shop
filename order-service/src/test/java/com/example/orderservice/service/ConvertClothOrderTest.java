package com.example.orderservice.service;

import com.example.orderservice.domain.ClothOrder;
import com.example.orderservice.domain.Order;
import com.example.orderservice.rest.dto.ClothOrderDto;
import com.example.orderservice.rest.dto.OrderDto;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Array;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import(ConvertClothOrderImpl.class)
class ConvertClothOrderTest {

    @Autowired
    private ConvertClothOrder convertClothOrder;



    @Test
    void dtoToOrder() {

        Order order1 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());
        order1.setAddress("Москва");
        order1.setEmailUser("daniil");
        order1.setId(1L);

        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");
        ClothOrder clothOrder1 = new ClothOrder();
        clothOrder1.setPrice(1999L);
        clothOrder1.setQuantity(4);
        clothOrder1.setSkuCode("clothe2_black_XL");
        order1.setClothOrders(List.of(clothOrder1, clothOrder));


        OrderDto orderDto = new OrderDto();
        orderDto.setEmailUser("daniil");
        orderDto.setAddress("Москва");

        ClothOrderDto clothOrderDto = new ClothOrderDto();
        clothOrderDto.setSize("XL");
        clothOrderDto.setName("clothe1");
        clothOrderDto.setColor("black");
        clothOrderDto.setQuantity(4);
        clothOrderDto.setPrice(1999L);

        ClothOrderDto clothOrderDto1 = new ClothOrderDto();
        clothOrderDto1.setSize("XL");
        clothOrderDto1.setName("clothe2");
        clothOrderDto1.setColor("black");
        clothOrderDto1.setQuantity(4);
        clothOrderDto1.setPrice(1999L);
        Collection<ClothOrderDto> clothOrderDtoList = List.of(clothOrderDto1,clothOrderDto);

        orderDto.setClothOrderDtoList(clothOrderDtoList);

        val expectedOrder = convertClothOrder.dtoToOrder(orderDto);
        assertThat(expectedOrder.getClothOrders()).isEqualTo(order1.getClothOrders());

    }

    @Test
    void orderToDto() {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress("Москва");
        order.setEmailUser("daniil");
        order.setId(1L);

        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");
        ClothOrder clothOrder1 = new ClothOrder();
        clothOrder1.setPrice(1999L);
        clothOrder1.setQuantity(4);
        clothOrder1.setSkuCode("clothe2_black_XL");
        order.setClothOrders(List.of(clothOrder1, clothOrder));


        OrderDto orderDto = new OrderDto();
        orderDto.setEmailUser("daniil");
        orderDto.setAddress("Москва");

        ClothOrderDto clothOrderDto = new ClothOrderDto();
        clothOrderDto.setSize("XL");
        clothOrderDto.setName("clothe1");
        clothOrderDto.setColor("black");
        clothOrderDto.setQuantity(4);
        clothOrderDto.setPrice(1999L);

        ClothOrderDto clothOrderDto1 = new ClothOrderDto();
        clothOrderDto1.setSize("XL");
        clothOrderDto1.setName("clothe2");
        clothOrderDto1.setColor("black");
        clothOrderDto1.setQuantity(4);
        clothOrderDto1.setPrice(1999L);
        Collection<ClothOrderDto> clothOrderDtoList = List.of(clothOrderDto1,clothOrderDto);

        orderDto.setClothOrderDtoList(clothOrderDtoList);

        assertThat(convertClothOrder.orderToDto(order).getClothOrderDtoList()).isEqualTo(orderDto.getClothOrderDtoList());
    }

    @Test
    void dtoClothToClothOrder() {
        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");


        ClothOrderDto clothOrderDto = new ClothOrderDto();
        clothOrderDto.setSize("XL");
        clothOrderDto.setName("clothe1");
        clothOrderDto.setColor("black");
        clothOrderDto.setQuantity(4);
        clothOrderDto.setPrice(1999L);

        assertThat(convertClothOrder.dtoClothToClothOrder(clothOrderDto)).isEqualTo(clothOrder);

    }

    @Test
    void clothOrderToDto() {

        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");


        ClothOrderDto clothOrderDto = new ClothOrderDto();
        clothOrderDto.setSize("XL");
        clothOrderDto.setName("clothe1");
        clothOrderDto.setColor("black");
        clothOrderDto.setQuantity(4);
        clothOrderDto.setPrice(1999L);

        assertThat(convertClothOrder.clothOrderToDto(clothOrder)).isEqualTo(clothOrderDto);
    }
}