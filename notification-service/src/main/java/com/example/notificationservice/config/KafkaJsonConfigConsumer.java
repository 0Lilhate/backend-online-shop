//package com.example.notificationservice.config;
//
//
//import com.example.notificationservice.model.Order;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.LongDeserializer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaJsonConfigConsumer {
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
//    private String kafkaGroupId;
//
//    @Bean
//    public KafkaListenerContainerFactory<?> batchFactory() {
//        ConcurrentKafkaListenerContainerFactory<Long, Order> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(true);
//        factory.setMessageConverter(new BatchMessagingMessageConverter(converter()));
//        return factory;
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<?> singleFactory() {
//        ConcurrentKafkaListenerContainerFactory<Long, Order> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setBatchListener(false);
//        factory.setMessageConverter(new StringJsonMessageConverter());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<Long, Order> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
//        return new ConcurrentKafkaListenerContainerFactory<>();
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
//        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
//        return props;
//    }
//
//    @Bean
//    public StringJsonMessageConverter converter() {
//        return new StringJsonMessageConverter();
//    }
//
//}