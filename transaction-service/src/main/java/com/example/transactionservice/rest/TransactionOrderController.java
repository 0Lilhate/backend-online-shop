package com.example.transactionservice.rest;

import com.example.transactionservice.service.TransactionServer;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import lombok.val;

import org.springframework.http.HttpHeaders;


import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class TransactionOrderController {

    private final TransactionServer transactionServer;

    @PostMapping("/api/charge/{email}")
    public Charge startTransaction(@PathVariable(name = "email") String email, @RequestHeader HttpHeaders headers){

        val authToken = SecurityContextHolder.getContext().getAuthentication();

//        HttpHeaders httpHeaders = request.getHeaders();

//        String token = request.getHeaders().get("token").toString();

//        String token = request.getHeaders("token").toString();
        String token = headers.get("token").toString();


        try {
            return transactionServer.charge(email,token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
