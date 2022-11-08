package com.example.transactionservice.service;

import com.example.transactionservice.common.AppConstants;
import com.example.transactionservice.config.OrderUtil;
import com.example.transactionservice.feign.OrderTransactionClient;
import com.example.transactionservice.model.Order;
import com.example.transactionservice.model.PaymentObject;
import com.example.transactionservice.stripe.StripeClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final ObjectMapper objectMapper;
    private KafkaTemplate<Object, PaymentObject> kafkaTemplate;

    private StripeClient stripeClient;
    private final OrderTransactionClient orderTransactionClient;


    @Override
    public Charge charge(String email, String token) throws Exception {
        Order order = orderTransactionClient.getOrderInTransaction(email);
        Long amount = OrderUtil.calculateOrderAmountInCents(order);

        Charge charge = null;

        if(token!=null){
            charge = stripeClient.chargeNewCard(token,amount);
        }

        PaymentObject paymentObject = new PaymentObject(order.getOrderNumber(),
                order.getEmailUser(), order.getAddress(), order.getClothOrders(), amount);
        kafkaTemplate.send(AppConstants.NOTIFICATION_TOPIC, paymentObject);


        return charge;

    }



    @Override
    public void consumerFromOrder(Order order) {
        System.out.println(order);

    }

//    private String writeValueAsString(Order order) {
//        try {
//            return objectMapper.writeValueAsString(order);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Writing value to JSON failed: " + order.toString());
//        }
//    }
}
