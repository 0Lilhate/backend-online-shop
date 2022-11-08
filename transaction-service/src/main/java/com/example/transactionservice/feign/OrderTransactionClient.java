package com.example.transactionservice.feign;

import com.example.transactionservice.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("order")
public interface OrderTransactionClient {
    @PostMapping("/api/order/transaction/{email}")
    Order getOrderInTransaction(@PathVariable("email") String email);
}