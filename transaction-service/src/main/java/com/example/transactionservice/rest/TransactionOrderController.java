package com.example.transactionservice.rest;

import com.example.transactionservice.service.TransactionServer;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class TransactionOrderController {

    private final TransactionServer transactionServer;

    @PostMapping("/api/charge/{email}")
    public Mono<Charge> startTransaction(@PathVariable(name = "email") String email, ServerHttpRequest request){

        HttpHeaders httpHeaders = request.getHeaders();

        String token = httpHeaders.get("token").toString();

        CompletableFuture<Charge> future = CompletableFuture.supplyAsync(
                ()->{
                    try {
                        return transactionServer.charge(email,token);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

        return Mono.fromFuture(future);

    }
}
