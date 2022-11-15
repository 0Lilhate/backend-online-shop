package com.example.transactionservice.component;

import com.example.transactionservice.model.CurrencyResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RequestCurrency {

    private final WebClient webClient = WebClient.create("https://api.apilayer.com");

    public Mono<CurrencyResponse> getConvertCurrency(String to, String from, String amount){
        return webClient.get().uri("/currency_data/convert",
                uriBuilder -> uriBuilder.queryParam("to", to)
                        .queryParam("from", from)
                        .queryParam("amount", amount)
                        .build()).accept(MediaType.APPLICATION_JSON)
                .header("apiKey", "x3JoKSLxRn7JCqxbH2gzrc2f3vhXADzc")
                .retrieve().bodyToMono(CurrencyResponse.class);
    }
}
