package com.example.transactionservice.service;


import com.example.transactionservice.model.Order;
import com.stripe.model.Charge;

public interface TransactionServer {
     void consumerFromOrder(Order order);

     Charge charge(String email, String token) throws Exception;
}
