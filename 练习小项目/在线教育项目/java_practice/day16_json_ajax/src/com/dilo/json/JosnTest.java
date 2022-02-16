package com.dilo.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JosnTest {

    @Test
    public void test1(){
        //javaBean 和 josn 互转
        Person person = new Person(1,"tom");

        //创建Gson对象,toJson方法可以把java对象转换为json对象
        Gson gson = new Gson();
        String personToJson = gson.toJson(person);
        System.out.println(personToJson);

        //fromJson 可以将json对象转换为javaBean对象
        Person person1 = gson.fromJson(personToJson,Person.class);
        System.out.println(person1);
    }

    //list 和json 互转
    @Test
    public void test2(){
        List<Person> list = new ArrayList<>();
        list.add(new Person(1,"tom"));
        list.add(new Person(2,"jerry"));
        list.add(new Person(3,"jone"));

        //创建Gson对象
        Gson gson = new Gson();

        String listToJson = gson.toJson(list);
        System.out.println(listToJson);

        //json 转换为 list
        List<Person> list1 = gson.fromJson(listToJson, new TypeToken<List<Person>>(){}.getType());
        System.out.println(list1);

    }

    //map 转换为 json

    @Test
    public void test3(){
        Map<Integer,Person> map = new HashMap<>();
        map.put(1,new Person(1,"tom"));
        map.put(2,new Person(2,"jerry"));
        map.put(3,new Person(3,"bob"));

        //创建gson对象,调用toJson方法
        Gson gson = new Gson();
        String mapToJson  = gson.toJson(map);
        System.out.println(mapToJson);

        // json 转换为 map
        Map<Integer,Person> map1 = gson.fromJson(mapToJson,new TypeToken<Map<Integer,Person>>(){}.getType());
        System.out.println(map1);

    }


}
