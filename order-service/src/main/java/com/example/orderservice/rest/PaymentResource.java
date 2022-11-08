package com.example.orderservice.rest;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class PaymentResource {

    private final KafkaProducerService kafkaProducerService;

    @PostMapping(value = "/api/order/transaction/{email}")
    @ResponseStatus(HttpStatus.OK)
    public Order makePayment(@PathVariable(name = "email") String email ){
        Order order = kafkaProducerService.transactionTopic(email);

        return order;
    }
}
