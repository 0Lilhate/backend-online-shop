package com.example.transactionservice.service;

import com.example.transactionservice.feign.OrderTransactionClient;
import com.example.transactionservice.model.ClothOrder;
import com.example.transactionservice.model.CurrencyResponse;
import com.example.transactionservice.model.Order;
import com.example.transactionservice.model.PaymentObject;
import com.example.transactionservice.stripe.StripeClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Charge;
import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.junit.Before;
import org.junit.ClassRule;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.springframework.kafka.test.assertj.KafkaConditions.key;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.hasValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class TransactionServerImplTest {

    private static final String TOPIC_NAME = "notification";
    private static final String EMAIL_USER = "daniil";
    private KafkaMessageListenerContainer<String, String> container;

    private BlockingQueue<ConsumerRecord<String, String>> records;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TransactionServer transactionServer;

    @MockBean
    private StripeClient stripeClient;

    @MockBean
    private OrderTransactionClient orderTransactionClient;

    @MockBean
    private ConvertCurrency convertCurrency;


    @BeforeAll
    void setUp() {
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(getConsumerProperties());
        ContainerProperties containerProperties = new ContainerProperties(TOPIC_NAME);
        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        records = new LinkedBlockingQueue<>();
        container.setupMessageListener((MessageListener<String, String>) record-> {


            records.add(record);
        });
        container.start();
        ContainerTestUtils.waitForAssignment(container, embeddedKafkaBroker.getPartitionsPerTopic());
    }



    @Test
    void charge() throws Exception {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setAddress("Москва");
        order.setEmailUser("daniil");

        ClothOrder clothOrder = new ClothOrder();
        clothOrder.setPrice(1999L);
        clothOrder.setQuantity(4);
        clothOrder.setSkuCode("clothe1_black_XL");
        ClothOrder clothOrder1 = new ClothOrder();
        clothOrder1.setPrice(1999L);
        clothOrder1.setQuantity(4);
        clothOrder1.setSkuCode("clothe2_black_XL");
        order.setClothOrders(List.of(clothOrder1, clothOrder));

        when(orderTransactionClient.getOrderInTransaction(EMAIL_USER)).thenReturn(order);

        CurrencyResponse currencyResponse = new CurrencyResponse();
        val info = new CurrencyResponse.Info();
        info.setQuote("59");
        info.setTimestamp("11");
        currencyResponse.setInfo(info);
        when(convertCurrency.convertCurrency(any(),any(),any())).thenReturn(Mono.just(currencyResponse));
        when(stripeClient.chargeNewCard("token", 1999L)).thenReturn(new Charge());

        transactionServer.charge("daniil", "token");

        ConsumerRecord<String, String> message = records.poll(10000000, TimeUnit.MICROSECONDS);
        assertThat(message).isNotNull();

        PaymentObject result = objectMapper.readValue(message.value(), PaymentObject.class);
        assertThat(result).isNotNull();




    }

    private Map<String, Object> getConsumerProperties(){
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, embeddedKafkaBroker.getBrokersAsString(),
                ConsumerConfig.GROUP_ID_CONFIG, "notificationId",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true",
                ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10",
                ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "60000",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    }

    @AfterAll
    void tearDown(){
        container.stop();
    }


}