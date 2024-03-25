package com.newsapp.authenticationserver.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "userServerTopic")
    public void listen(ConsumerRecord<String, String> record) {
        log.info("Received Message: " + record.value());
        // Add your business logic to process the received message
    }
}