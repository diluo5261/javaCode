package com.dilo.ba04;

public class School {
    private String name;
    private String address;

    public School() {
        System.out.println("school 的无参构造方法!");
    }

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        System.out.println("school的setName()方法");
        this.name = name;
    }

    public void setAddress(String address) {
        System.out.println("school的setAddress()方法");
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
