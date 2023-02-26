package br.com.Consumidor.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ConsumerListener {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(String message)
    {
        log.info("THREAD {}", Thread.currentThread().getId() );
        log.info("RECEIVE {}", message);



    }
}
