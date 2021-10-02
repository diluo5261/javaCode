package com.dilo.ba05;

public class School {
    private String name;
    private String address;

    public School() {

//        System.out.println("school 无参构造");
    }

    public School(String name, String address) {
//        System.out.println("school 有参构造");
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
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
