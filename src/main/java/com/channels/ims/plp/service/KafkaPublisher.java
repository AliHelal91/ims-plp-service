package com.channels.ims.plp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;


    /**
     * Send Message to Kafka Topic
     *
     * @param topic   Kafka Topic
     * @param message Message
     */
    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
