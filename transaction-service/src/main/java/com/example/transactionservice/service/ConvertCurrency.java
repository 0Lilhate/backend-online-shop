package com.example.transactionservice.service;

import com.example.transactionservice.model.CurrencyResponse;
import reactor.core.publisher.Mono;

public interface ConvertCurrency {
    Mono<CurrencyResponse> convertCurrency(String to, String from, String amount);
}
