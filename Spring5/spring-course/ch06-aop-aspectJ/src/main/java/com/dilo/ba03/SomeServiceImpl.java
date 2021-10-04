package com.dilo.ba03;

import com.dilo.ba02.Student;

public class SomeServiceImpl implements SomeService {

    @Override
    public void doSome(String name, Integer age) {
        System.out.println("目标方法的 doSome");


    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("目标方法的 doOther");
        return "abc";
    }

    @Override
    public Student doOther2(String name, Integer age) {
       Student stu = new Student(name,age);


        return stu;
    }

    @Override
    public String doFirst(String name, Integer age) {
        return name+age;
    }
}
