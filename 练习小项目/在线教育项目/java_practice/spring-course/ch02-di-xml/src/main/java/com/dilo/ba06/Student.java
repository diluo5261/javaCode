package com.dilo.ba06;

public class Student {

    private String name;
    private int age;

    //声明一个引用数据类型
    private School school;



    public Student() {
        System.out.println("Student 无参构造方法");
    }

    public Student(String name, int age, School school) {
        System.out.println("Student 有参构造方法");
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
