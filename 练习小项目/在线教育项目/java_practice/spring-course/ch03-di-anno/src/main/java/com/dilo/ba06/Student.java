package com.dilo.ba06;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component()
public class Student {
    @Value("张三")
    private String name;

    @Value("15")
    private Integer age;

    /*
    引用类型
    @Resource:来自jdk种的注解,spring框架提供了对这个注解的功能支持,可以使用它给引用类型赋值
                使用的也是自动注入原理,支持byName , byType ,默认是 byName

    位置:
        1.在属性定义的上面,无需set方法,推荐使用.
        2.在set方法的上面

     先使用byName 自动注入,如果自动注入失败,在使用byType

     只是用byName方式,需要增加一个属性 name
     name的是是bean的id(名称)

     */




//    @Resource(name = "school")
   @Resource
    private School school;

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
                ", school=" + school +
                '}';
    }
}
