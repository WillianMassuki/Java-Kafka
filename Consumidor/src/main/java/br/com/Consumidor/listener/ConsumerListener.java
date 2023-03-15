package br.com.Consumidor.listener;


import br.com.Consumidor.custom.PersonCustonLIstener;
import br.com.Consumidor.model.City;
import br.com.Consumidor.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @KafkaListener(topics = "topic-1", groupId = "group-1", concurrency = "2")
    public void listener(String message)
    {
        // log.info("THREAD {}", Thread.currentThread().getId() );
        log.info(message);

    }

    @KafkaListener(topics = "topic-1", groupId = "group-1", concurrency = "2")
    public void listener4(List<String> messages)
    {
        // log.info("THREAD {}", Thread.currentThread().getId() );
        log.info(messages.toString());

    }

    @KafkaListener(topicPartitions = {@TopicPartition(topic = "my-topic", partitions = "0")}, groupId = "group-2")
    public void listener2(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition)
    {
        // log.info("THREAD {}", Thread.currentThread().getId() );
        log.info(message);

    }


  //  @KafkaListener(topics = "person-topic", groupId = "group-1", containerFactory = "personKafkaListenerContainerFactory")
    @PersonCustonLIstener
    public void listen(Person person)
    {
        log.info(" Pessoa: {} " + person);

    }

    @KafkaListener(topics = "city-topic", groupId = "group-1", containerFactory = "jsonKaftkaListenerContainerFactory")
    public void listen(List<City> cities)
    {
        log.info(" City: {} " + cities);

    }

    @KafkaListener(topics = "city-topic", groupId = "group-1", containerFactory = "jsonKaftkaListenerContainerFactory")
    public void listenM(List<Message<City>> messages)
    {
        log.info(" Messages: {} " + messages);
        var city = messages.get(0).getPayload();
        log.info(" Cidades: {} " + city);
        log.info(" Cidades: {} " + messages.get(0).getHeaders());


    }
}
