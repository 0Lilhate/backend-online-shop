package com.example.transactionservice.service;


import com.example.transactionservice.model.Order;
import com.stripe.model.Charge;

public interface TransactionServer {

     Charge charge(String email, String token) throws Exception;
}
