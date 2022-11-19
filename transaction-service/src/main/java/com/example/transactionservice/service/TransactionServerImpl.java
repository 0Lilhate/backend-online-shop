package com.example.transactionservice.service;

import com.example.transactionservice.common.AppConstants;
import com.example.transactionservice.config.OrderUtil;
import com.example.transactionservice.feign.OrderTransactionClient;
import com.example.transactionservice.model.CurrencyResponse;
import com.example.transactionservice.model.Order;
import com.example.transactionservice.model.PaymentObject;
import com.example.transactionservice.stripe.StripeClient;
import com.stripe.model.Charge;
import lombok.AllArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@AllArgsConstructor
public class TransactionServerImpl implements TransactionServer {

    private KafkaTemplate<Object, PaymentObject> kafkaTemplate;

    private StripeClient stripeClient;
    private final OrderTransactionClient orderTransactionClient;
    private final ConvertCurrency convertCurrency;


    @Override
    public Charge charge(String email, String token) throws Exception {
        Order order = orderTransactionClient.getOrderInTransaction(email);
        Long amount = OrderUtil.calculateOrderAmountInCents(order);

        CurrencyResponse currencyResponse = convertCurrency.convertCurrency("USD", "RUB", amount.toString());

        Charge charge = new Charge();

        Double usd = Double.parseDouble(currencyResponse.getInfo().getQuote());

        if(token!=null){
            charge = stripeClient.chargeNewCard(token,
                    amount * usd);
        }

        PaymentObject paymentObject = new PaymentObject(order.getOrderNumber(),
                order.getEmailUser(), order.getAddress(), order.getClothOrders(), (long) (amount * usd));
        kafkaTemplate.send(AppConstants.NOTIFICATION_TOPIC, paymentObject);


        return charge;

    }






}
