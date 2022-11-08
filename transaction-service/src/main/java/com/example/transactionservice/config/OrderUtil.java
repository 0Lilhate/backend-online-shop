package com.example.transactionservice.config;

import com.example.transactionservice.model.ClothOrder;
import com.example.transactionservice.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderUtil {
    public static Long calculateOrderAmountInCents(Order order){
        BigDecimal amount = new BigDecimal(0);

        if (order != null && order.getClothOrders() != null) {
            for (ClothOrder orderLineItemDto:order.getClothOrders()) {
                if(orderLineItemDto.getQuantity()>0){
                    orderLineItemDto.getQuantity();

                    BigDecimal price = BigDecimal.valueOf(orderLineItemDto.getPrice())
                            .multiply(BigDecimal.valueOf(orderLineItemDto.getQuantity()));

                    amount = amount.add( price.multiply(BigDecimal.valueOf( 0.16)));
                }
            }

        }

        amount = amount.multiply(new BigDecimal(100));

        return amount.longValue();
    }
}