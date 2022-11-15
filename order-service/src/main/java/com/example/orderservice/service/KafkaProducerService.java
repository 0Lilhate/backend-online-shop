package com.example.orderservice.service;

import com.example.orderservice.domain.Order;

public interface KafkaProducerService {
    public Order transactionTopic(String email);

}
