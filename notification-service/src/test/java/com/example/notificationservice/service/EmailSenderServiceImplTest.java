package com.example.notificationservice.service;

import com.example.notificationservice.model.PaymentObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.shaded.org.awaitility.Durations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;


@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
@Import(EmailSenderServiceImpl.class)
class EmailSenderServiceImplTest {

    private final String TOPIC_NAME = "notification";

    private Producer<String, PaymentObject> producer;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailSenderService emailSenderService;






    @Captor
    ArgumentCaptor<PaymentObject> paymentArgumentCaptor;

//    @Captor
//    ArgumentCaptor<String> topicArgumentCaptor;
//
//    @Captor
//    ArgumentCaptor<Integer> partitionArgumentCaptor;
//
//    @Captor
//    ArgumentCaptor<Long> offsetArgumentCaptor;


    @BeforeAll
    void setUp(){
        Map<String, Object> configs = KafkaTestUtils.producerProps(embeddedKafkaBroker.getBrokersAsString());
        producer = new KafkaProducer(configs, new StringSerializer(), new JsonSerializer<PaymentObject>());
    }


    @Test
    void sendEmailMessage() throws JsonProcessingException {

        String uuid = "notificationId";
        val paymentObject = new PaymentObject();
        paymentObject.setEmailUser("daniil");
        paymentObject.setAmountPayment(1999L);

        String message = objectMapper.writeValueAsString(paymentObject);
        producer.send(new ProducerRecord(TOPIC_NAME, "", paymentObject));





        producer.close();
    }

    @AfterAll
    void shutdown() {
        producer.close();
    }
}