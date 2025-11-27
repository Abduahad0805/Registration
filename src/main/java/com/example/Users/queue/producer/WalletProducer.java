package com.example.Users.queue.producer;

import com.example.Users.config.topic.Topics;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class WalletProducer {

    private static final KafkaTemplate<Object, String> kafkaTemplate = null;

    public static void sendWallet(String text){
        kafkaTemplate.send(Topics.WALLET, text);
        System.out.println("Отправлен" + text);
    }
}
