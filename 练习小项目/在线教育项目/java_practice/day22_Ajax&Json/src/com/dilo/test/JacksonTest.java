package com.dilo.test;

import com.dilo.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JacksonTest {

    //将java对象转换为JSON字符串
    @Test
    public void test1() throws JsonProcessingException {
        //1. 创建Person对象
        Person p = new Person("zhangsan",23,"男");

        //2.创建Jackson的核心对象
        ObjectMapper mapper = new ObjectMapper();
        //3.调用转换方法
        String json = mapper.writeValueAsString(p);
        System.out.println(json);
    }

    /*
    转换方法:
        writeValue(参数1,obj):
            参数1:
                File:将obj对象转换为JSON字符串,并保存到指定的文件中
                Write:将obj对象转换为JSON字符串,并将json数据填充到字符输出流中
                Outputstream:将obj对象转换为json字符串,并将json字符串填充到字节输出流当中
        writeValueAsStream:将对象转化为字符串
     */


    @Test
    public void test2() throws IOException {
        //1. 创建Person对象
        Person p = new Person("zhangsan",23,"男");

        //2.创建Jackson对象
        ObjectMapper mapper = new ObjectMapper();

        //3.调用转换方法
        //将数据写入到d:/a.txt文件中
//        mapper.writeValue(new File("d://a.txt"),p);

        //writeValue.将数据关联到Write中
        mapper.writeValue(new FileWriter("d://b.txt"),p);


    }

    @Test
    public void test3() throws JsonProcessingException {
        //1.创建Person对象
        Person person = new Person("张三",23,"男",new Date());

        //2.转化
        ObjectMapper mapper = new ObjectMapper();
        String json= mapper.writeValueAsString(person);
        System.out.println(json);
    }

    //复杂对象的转换
    //List数组
    @Test
    public void test4() throws JsonProcessingException {
        //1.创建Person对象
        Person p1 = new Person("张三",15,"男",new Date());
        Person p2 = new Person("李四",15,"男",new Date());
        Person p3 = new Person("王五",15,"男",new Date());

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        //2.创建ObjectManager对象
        ObjectMapper mapper = new ObjectMapper();
        //3.转换
        String json = mapper.writeValueAsString(list);
        System.out.println(list);
    }

    @Test
    public void test5() throws JsonProcessingException {
        //1.创建map对象
        Map<String,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",23);
        map.put("gender",true);

        //2.创建ObjectMapper对象
        ObjectMapper mapper =new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        System.out.println(json);
    }

    //JSON字符串转换为Java对象
    @Test
    public void test6() throws IOException {
        String json = "{\"name\":\"张三\",\"age\":23,\"gender\":\"男\",\"birthday\":\"2021-09-10\"}";
        //2.创建ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();

        //3.转换为java都西昂
        Person person = mapper.readValue(json,Person.class);
        System.out.println(person);
    }

}
