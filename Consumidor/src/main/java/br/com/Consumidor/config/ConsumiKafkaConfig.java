package br.com.Consumidor.config;

import java.util.HashMap;

import br.com.Consumidor.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Slf4j
@EnableKafka
@Configuration
public class ConsumiKafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, String> consumiFactory()
    {
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumiFactory());
        factory.setConcurrency(2);
        factory.setBatchListener(true);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Person> personConsumerFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        var jsonDeserializer = new JsonDeserializer<>(Person.class)
                .trustedPackages("*")
                .forKeys();
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Person> personKafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Person>();
        factory.setConsumerFactory(personConsumerFactory());
        factory.setRecordInterceptor(AdultInterceptor());
        factory.setRecordInterceptor(exampleInterceptor());
        return factory;
    }

    private RecordInterceptor<String, Person> exampleInterceptor() {

        return new RecordInterceptor<String, Person>() {
            @Override
            public ConsumerRecord<String, Person> intercept(ConsumerRecord<String, Person> consumerRecord) {
                return consumerRecord;
            }

            @Override
            public void success(ConsumerRecord<String, Person> record, Consumer<String, Person> consumer) {
                log.info("sucesso");
            }
        };
    }

    private RecordInterceptor<String, Person> AdultInterceptor() {

        return record  ->
        {
         log.info("Record: {} ", record);

         return  record;
        };
    }


    @Bean
    public ConsumerFactory jsonConsumerFactory()
    {
        var configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(configs);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory jsonKaftkaListenerContainerFactory()
    {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(jsonConsumerFactory());
   //     factory.setMessageConverter(new JsonMessageConverter());
        factory.setMessageConverter(new BatchMessagingMessageConverter( new JsonMessageConverter()));
        factory.setBatchListener(true);
        return factory;
    }
}
