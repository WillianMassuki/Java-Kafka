package br.com.Consumidor3.listerner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer2Listerner {

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(String message)
    {
        log.info("THREAD {}", Thread.currentThread().getId() );
        log.info("RECEIVE {}", message);



    }
}
