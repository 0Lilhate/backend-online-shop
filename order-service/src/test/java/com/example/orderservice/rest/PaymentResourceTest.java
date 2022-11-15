package com.example.orderservice.rest;

import com.example.orderservice.domain.Order;
import com.example.orderservice.rest.dto.OrderDto;
import com.example.orderservice.service.KafkaProducerService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockOpaqueToken;



@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebFluxTest(PaymentResource.class)
@ActiveProfiles("test")
class PaymentResourceTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @Test
    void makePayment() {

        val order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress(UUID.randomUUID().toString());
        order.setEmailUser("daniil");
        order.setId(1L);
        order.setClothOrders(Collections.emptyList());

        when(kafkaProducerService.transactionTopic(any())).thenReturn(order);


        webTestClient.mutateWith(mockOpaqueToken().authorities(new SimpleGrantedAuthority("admin")))
                .mutateWith(csrf())
                .post().uri("/api/order/transaction/daniil")
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderDto.class)
                .value(o -> {

                    assertThat(o.getEmailUser()).isEqualTo(order.getEmailUser());
                });
    }
}