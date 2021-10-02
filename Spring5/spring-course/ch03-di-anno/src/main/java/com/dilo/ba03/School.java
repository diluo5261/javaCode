package com.dilo.ba03;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class School {
    @Value("工业大学")
    private String name;

    @Value("天津")
    private String address;

    public School() {
        System.out.println("school 无参构造");
    }

    public School(String name, String address) {
        System.out.println("school 有参构造");
        this.name = name;
        this.address = address;
    }



    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
