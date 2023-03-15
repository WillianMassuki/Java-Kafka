package br.com.Produtor.model;

import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City implements Serializable {


    private String name;
    private String uf;
}
