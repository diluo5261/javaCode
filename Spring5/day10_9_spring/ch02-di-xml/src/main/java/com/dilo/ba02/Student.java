package com.dilo.ba02;

public class Student {
    private String name;
    private Integer age;
    private School school;

    public Student() {
        System.out.println("student 的无参构造器!!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("student 的setName()");
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("student 的setAge()");
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
