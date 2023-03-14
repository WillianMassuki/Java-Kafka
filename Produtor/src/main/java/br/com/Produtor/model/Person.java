package br.com.Produtor.model;

import lombok.*;

import java.awt.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {

    private String name;
    private Integer age;


}
