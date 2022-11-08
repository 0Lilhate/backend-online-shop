//package com.example.transactionservice.config;
//
//
//import com.example.transactionservice.model.Order;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.LongSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaJsonConfigProducer {
//
////    public ProducerFactory<String, Order> producerFactoryJson() {
////        Map<String, Object> config = new HashMap<>();
////        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
////        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // disable autocommit
////        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////        return new DefaultKafkaProducerFactory<>(config);
////    }
////
////    @Bean(name = "kafkaJsonTemplate")
////    public KafkaTemplate<String, Order> kafkaTemplateJson() {
////        return new KafkaTemplate<>(producerFactoryJson());
////    }
//
//    @Value("${kafka.server}")
//    private String kafkaServer;
//
//    @Value("${kafka.group.id}")
//    private String kafkaProducerId;
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<Long, Order> producerStarshipFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<Long, Order> kafkaTemplate() {
//        KafkaTemplate<Long, Order> template = new KafkaTemplate<>(producerStarshipFactory());
//        template.setMessageConverter(new StringJsonMessageConverter());
//        return template;
//    }
//}