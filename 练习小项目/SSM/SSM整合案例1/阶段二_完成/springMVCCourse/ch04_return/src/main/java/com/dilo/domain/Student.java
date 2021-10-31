package com.dilo.domain;

public class Student {
    //要求属性名和请求参数中的名字一样
    private String rname;
    private Integer rage;

    public Student() {
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Integer getRage() {
        return rage;
    }

    public void setRage(Integer rage) {
        this.rage = rage;
    }
}
