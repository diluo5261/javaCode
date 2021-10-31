package com.dilo.domain;

public class Student {
    //要求属性名和请求参数中的名字一样
    private String rname;
    private String rage;

    public Student() {
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRage() {
        return rage;
    }

    public void setRage(String rage) {
        this.rage = rage;
    }
}
