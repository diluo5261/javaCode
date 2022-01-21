package com.dilo.domain;

import lombok.Data;

//lombok
@Data
public class Book {
    private Integer id;
    private String author;
    private String name;
    private double price;

}
