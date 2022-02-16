package com.dilo.ba02;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*
    @Value :简单类型的属性赋值
    属性:value 是 String 类型的, 表示简单类型的属性值
    位置 : 1. 在属性定义的上面,无需set方法,推荐使用
           2.在set 方法的上面

 */

@Component
public class Student {
    @Value("张三")
    private String name;

    @Value("15")
    private Integer age;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }*/

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
