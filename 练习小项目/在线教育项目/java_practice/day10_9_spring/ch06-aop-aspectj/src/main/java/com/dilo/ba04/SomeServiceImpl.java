package com.dilo.ba04;

public class SomeServiceImpl implements SomeService {


    @Override
    public void doSome(String name, Integer age) {
        System.out.println("===目标方法 的 doSome()=====");
        System.out.println("姓名:" + name + "   年龄" + age);
    }

    @Override
    public String doOther(String name, Integer age) {
        System.out.println("===目标方法 的 doSome()=====");
        System.out.println("姓名:" + name + "   年龄" + age);
        return name+age;
    }

}
