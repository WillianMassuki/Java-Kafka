package br.com.Produtor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;

@RestController
public class ProdutoController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public ResponseEntity<String> send()
    {
        kafkaTemplate.send("topic-1", "envio de " + LocalDateTime.now());

        return ResponseEntity.ok().build();
    }
}

