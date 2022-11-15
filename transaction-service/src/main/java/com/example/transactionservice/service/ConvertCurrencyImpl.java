package com.example.transactionservice.service;

import com.example.transactionservice.component.RequestCurrency;
import com.example.transactionservice.model.CurrencyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConvertCurrencyImpl implements ConvertCurrency{

    private final RequestCurrency requestCurrency;


    @Override
    @CacheEvict(value = "convert", key = "{#to,#from}")
    public Mono<CurrencyResponse> convertCurrency(String to, String from, String amount) {
        return requestCurrency.getConvertCurrency(to,from,amount);
    }

    @CacheEvict(value = "convert", allEntries = true)
    @Scheduled(cron = "0 0 */8 * * ?")
    public void updateCache(){
        System.out.println("очистка кэша");
    }
}
