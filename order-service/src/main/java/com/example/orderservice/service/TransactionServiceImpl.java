package com.example.orderservice.service;

import com.example.orderservice.domain.Order;
import com.example.orderservice.exception.NotFoundException;
import com.example.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionrService {

//    private final String topicTransaction = "transaction";
    private final OrderRepository orderRepository;




    @Override
    public Order transactionTopic(String email) {
        Order order = orderRepository.findByEmailUser(email).orElseThrow(()-> new NotFoundException(email));

        orderRepository.delete(order);
        log.info("Transaction topic message is {}", order);
        return order;
    }


}
