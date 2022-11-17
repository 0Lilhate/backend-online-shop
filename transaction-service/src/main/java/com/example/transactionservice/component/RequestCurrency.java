package com.example.transactionservice.component;

import com.example.transactionservice.model.CurrencyResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestCurrency {

//    private final WebClient webClient = WebClient.create("https://api.apilayer.com");
//
//    public Mono<CurrencyResponse> getConvertCurrency(String to, String from, String amount){
//        return webClient.get().uri("/currency_data/convert",
//                uriBuilder -> uriBuilder.queryParam("to", to)
//                        .queryParam("from", from)
//                        .queryParam("amount", amount)
//                        .build()).accept(MediaType.APPLICATION_JSON)
//                .header("apiKey", "x3JoKSLxRn7JCqxbH2gzrc2f3vhXADzc")
//                .retrieve().bodyToMono(CurrencyResponse.class);
//    }

    private RestTemplate restTemplate;

    public CurrencyResponse getConvertCurrency(String to, String from, String amount){
        restTemplate = new RestTemplate();
        String url = "https://api.apilayer.com/currency_data/convert?to={to}&from={from}&amount={amount}";
        Map<String, String> param = new HashMap<>();
        param.put("to", to);
        param.put("from", from);
        param.put("amount", amount);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("apiKey", "x3JoKSLxRn7JCqxbH2gzrc2f3vhXADzc");

        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<CurrencyResponse> response = restTemplate.exchange(url,
                HttpMethod.GET, requestEntity, CurrencyResponse.class, param);
        CurrencyResponse res = response.getBody();
        return res;
    }
}
