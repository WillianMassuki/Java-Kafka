package br.com.Consumidor.custom;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@KafkaListener
public @interface PersonCustonLIstener {

    @AliasFor(annotation  = KafkaListener.class, attribute = "groupid")
    String groupid() default "";

    @AliasFor(annotation  = KafkaListener.class, attribute = "topics")
    String[] topics() default "person-topic";

    @AliasFor(annotation  = KafkaListener.class, attribute = "containerFactory")
    String containerFactory() default "personKafkaListenerContainerFactory";



}
