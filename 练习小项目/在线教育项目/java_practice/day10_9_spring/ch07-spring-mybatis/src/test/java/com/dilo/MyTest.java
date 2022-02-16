package com.dilo;

import com.dilo.domain.Student;
import com.dilo.service.StudentService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test(){
        //读取配置文件，获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        String[] names = context.getBeanDefinitionNames();
        System.out.println(names);
    }

    @Test
    public void test01(){
        //读取配置文件，获取sping 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //根据 id 获取对象
        StudentService studentService = (StudentService) context.getBean("studentService");

        Student student = new Student(1005,"拉克丝","lakesi@163.com",15);
        int num = studentService.addStudent(student);
        System.out.println("成功插入"+num+"条数据");
    }

    @Test
    public void test02(){
        //读取配置文件，获取spring 容器对象
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //根据 id 获取对象
         StudentService service = (StudentService) context.getBean("studentService");

        List<Student> studentList = service.queryStudent();

        for (Student student : studentList){
            System.out.println(student);
        }
    }
}
