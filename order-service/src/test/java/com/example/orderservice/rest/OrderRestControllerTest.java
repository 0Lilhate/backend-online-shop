package com.example.orderservice.rest;

import com.example.orderservice.domain.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.rest.dto.OrderDto;
import com.example.orderservice.service.ConvertClothOrder;
import com.example.orderservice.service.OrderService;
import lombok.val;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import reactor.core.publisher.Mono;


import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.*;

@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebFluxTest(OrderRestController.class)
@ActiveProfiles("test")
class OrderRestControllerTest {


    @MockBean
    private OrderService orderService;

    @MockBean
    private ConvertClothOrder convertClothOrder;


    @Autowired
    private WebTestClient webTestClient;

//    @Autowired
//    public void setUp(final ApplicationContext applicationContext) {
//        webTestClient = WebTestClient
//                .bindToApplicationContext(applicationContext) // 2
//                .apply(springSecurity())  // 3
//                .configureClient()
//                .build();
//    }



    @Test
    void placeOrder() {
        OrderDto orderDto1 = new OrderDto();
        orderDto1.setEmailUser("daniil");

        Order order1 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());
        order1.setAddress(UUID.randomUUID().toString());
        order1.setEmailUser("daniil");
        order1.setId(1L);
        order1.setClothOrders(Collections.emptyList());

        when(convertClothOrder.orderToDto(any())).thenReturn(orderDto1);
        when(orderService.placeOrder(any())).thenReturn(order1);


        webTestClient.mutateWith(mockOpaqueToken().authorities(new SimpleGrantedAuthority("admin")))
                .mutateWith(csrf())
                .post().uri("/api/order")
                .body(Mono.just(orderDto1), OrderDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(OrderDto.class)
                .value(orderDtos -> {
                    System.out.println(orderDtos);
                    assertThat(orderDtos.getEmailUser()).isEqualTo(orderDto1.getEmailUser());
                });


    }

    @Test
//    @WithMockUser(username = "user", authorities = "ROLE_admin")
    void getOrder() {

        Order order1 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());
        order1.setAddress(UUID.randomUUID().toString());
        order1.setEmailUser(UUID.randomUUID().toString());
        order1.setId(1L);
        order1.setClothOrders(Collections.emptyList());

        Order order2 = new Order();
        order1.setOrderNumber(UUID.randomUUID().toString());
        order1.setAddress(UUID.randomUUID().toString());
        order1.setEmailUser(UUID.randomUUID().toString());
        order1.setId(2L);
        order1.setClothOrders(Collections.emptyList());


        when(orderService.getAllOrder()).thenReturn(List.of(order1, order2));

        OrderDto orderDto1 = new OrderDto();
        orderDto1.setEmailUser("daniil");
        OrderDto orderDto2 = new OrderDto();
        orderDto2.setEmailUser("egor");


        when(convertClothOrder.orderToDto(order1)).thenReturn(orderDto1);
        when(convertClothOrder.orderToDto(order2)).thenReturn(orderDto2);




//        webTestClient.mutateWith(mockJwt().authorities(new SimpleGrantedAuthority("admin")))
        webTestClient.mutateWith(mockOpaqueToken().authorities(new SimpleGrantedAuthority("admin")))
                .get().uri("/api/order")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(OrderDto.class)
                .value(orderDtos -> {
                    assertThat(orderDtos.get(0).getAddress()).isEqualTo(orderDto1.getAddress());
                    assertThat(orderDtos.get(1).getAddress()).isEqualTo(orderDto2.getAddress());
                });

    }

    @Test
    void addOneQuantityInClotheUser() {

        val orderDto1 = new OrderDto();
        orderDto1.setEmailUser("daniil");

        val order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress(UUID.randomUUID().toString());
        order.setEmailUser("daniil");
        order.setId(1L);
        order.setClothOrders(Collections.emptyList());

        when(convertClothOrder.orderToDto(any())).thenReturn(orderDto1);
        when(orderService.addOneQuantityInClothe(any(), any())).thenReturn(order);

        String sku_code = "a_a_b";


        webTestClient.mutateWith(mockOpaqueToken().authorities(new SimpleGrantedAuthority("admin")))
                .mutateWith(csrf())
                .put().uri("/api/order/clothe/add_item/daniil?sku_code=a_a_a")
                .accept()
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderDto.class)
                .value(orderDtos -> {
                    System.out.println(orderDtos);
                    assertThat(orderDtos.getEmailUser()).isEqualTo(orderDto1.getEmailUser());
                });

    }

    @Test
    void addNewClotheInOrder() {

        val orderDto = new OrderDto();
        orderDto.setEmailUser("daniil");

        val order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress(UUID.randomUUID().toString());
        order.setEmailUser("daniil");
        order.setId(1L);
        order.setClothOrders(Collections.emptyList());

        when(convertClothOrder.orderToDto(any())).thenReturn(orderDto);
        when(convertClothOrder.dtoToOrder(any())).thenReturn(order);
        when(orderService.addNewClothe(any(), any())).thenReturn(order);


        webTestClient.mutateWith(mockOpaqueToken().authorities(new SimpleGrantedAuthority("admin")))
                .mutateWith(csrf())
                .put().uri("/api/order/clothe/create/daniil")
                .body(Mono.just(orderDto), OrderDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderDto.class)
                .value(orderDtos -> {
                    System.out.println(orderDtos);
                    assertThat(orderDtos.getEmailUser()).isEqualTo(orderDto.getEmailUser());
                });
    }

    @Test
    void removeClotheInUser() {

        val orderDto = new OrderDto();
        orderDto.setEmailUser("daniil");

        val order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress(UUID.randomUUID().toString());
        order.setEmailUser("daniil");
        order.setId(1L);
        order.setClothOrders(Collections.emptyList());

        when(convertClothOrder.orderToDto(any())).thenReturn(orderDto);
        when(orderService.removeClothe(any(), any())).thenReturn(order);


        webTestClient.mutateWith(mockOpaqueToken().authorities(new SimpleGrantedAuthority("admin")))
                .mutateWith(csrf())
                .put().uri("/api/order/clothe/add_item/daniil?sku_code=a_a_a")
                .accept()
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderDto.class)
                .value(orderDtos -> {
                    System.out.println(orderDtos);
                    assertThat(orderDtos.getEmailUser()).isEqualTo(orderDto.getEmailUser());
                });
    }
}