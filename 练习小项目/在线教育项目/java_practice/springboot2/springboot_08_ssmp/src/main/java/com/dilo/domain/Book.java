package com.dilo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private Integer id;
    private String author;
    private String name;
    private double price;

}
