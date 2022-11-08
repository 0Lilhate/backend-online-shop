package com.example.transactionservice.rest;

import com.example.transactionservice.service.KafkaService;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class TransactionOrderController {

    private final KafkaService kafkaService;

    @PostMapping("/api/charge/{email}")
    public Charge startTransaction(@PathVariable(name = "email") String email, HttpServletRequest request){
        String token = request.getHeader("token");

        try {
            return kafkaService.charge(email,token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
