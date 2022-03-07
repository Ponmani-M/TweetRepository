package com.tweet.service.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.tweet.service.dto.TweetDto;

import java.util.HashMap;
import java.util.Map;

/**
 * **TODO
 * Configure all the beans as described below to send messages to Kafka Message broker.
 * Add appropriate annotations to this Bean Configuration class
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;


    /**
     * TODO**
     * Create a Bean of type Map<String, Object> containing the configuration
     * of Kafka Consumer.The configuration should include bootstrap server, message key and value Deserializers
     * message key should be of type String and Value should be of type Json containing Order details
     *  present in OrderDto.
     *  Also configure the required properties like TRUSTED_PACKAGES, GROUP_ID_CONFIG etc for the consumer
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "TweetMsg");
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return props;
    }

    /**
     * TODO**
     * Create a Bean of ConsumerFactory<String, LoanDetailsDto> using the above
     * consumer configuration
     */
    @Bean
    public ConsumerFactory<String, TweetDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(TweetDto.class, false));
    }

    /**
     * TODO**
     * Create a Bean of ConcurrentKafkaListenerContainerFactory<String, LoanDetailsDto> using the above
     * consumer factory configuration
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TweetDto> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TweetDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
