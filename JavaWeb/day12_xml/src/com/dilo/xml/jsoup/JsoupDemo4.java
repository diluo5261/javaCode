package com.dilo.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/*
- getElementsByTag（String  tagName）：根据id属性值后去唯一的element对象
>- getElementsByTag(String tagName) :根据标签名称获取元素对象集合
>- getElementsByAttribute（String key）：根据属性名称获取元素对象集合
>- getElementsByAttributeValue（）：根据对应的属性名和属性值获取元素对象集合
*
*
* */

public class JsoupDemo4 {
    public static void main(String[] args) throws IOException {
        //1、获取Student。xml的路径
        String str = JsoupDemo4.class.getClassLoader().getResource("student.xml").getPath();

        //2、获取Document对象
        Document document = Jsoup.parse(new File(str),"UTF-8");

        //通过Document对象获取name标签，获取所有的name标签，可以获取两个
        Elements elements = document.getElementsByTag("name");
        System.out.println(elements);
        System.out.println("----------------------------");

        //通过Element对象获取子标签对象
        Element ele_student = document.getElementsByTag("student").get(0);
//        System.out.println(ele_student);

        Elements ele_name = ele_student.getElementsByTag("name");
        System.out.println(ele_name);

        System.out.println("----------------------");


        //获取student对象的属性值
        String number = ele_student.attr("number");
        System.out.println(number);


        //获取文本内容




    }
}
