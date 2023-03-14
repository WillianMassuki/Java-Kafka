package br.com.Consumidor.listener;


import br.com.Consumidor.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ConsumerListener {

    /*
      public void listener(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                         @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int particao)
     */
/*
    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(String message, ConsumerRecordMetadata consumerRecordMetadata)
    {
       // log.info("THREAD {}", Thread.currentThread().getId() );
        log.info("RECEIVE {}", message);

    }

 */

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void listener(String message)
    {
        // log.info("THREAD {}", Thread.currentThread().getId() );
        log.info(message);

    }


    @KafkaListener(topics = "person-topic", groupId = "group-1", containerFactory = "personKafkaListenerContainerFactory")
    public void listen(Person person)
    {
        log.info(" Pessoa: {} " + person);

    }
}
