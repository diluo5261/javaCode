package com.dilo.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo3 {
    public static void main(String[] args) throws IOException {
        //1、获取Student。xml的路径
        String str = JsoupDemo3.class.getClassLoader().getResource("student.xml").getPath();

        //2、获取Document对象
        Document document = Jsoup.parse(new File(str),"UTF-8");

        //3、获取元素对象
        //3.1 获取所有student对象
        Elements elements = document.getElementsByTag("student");
        System.out.println(elements);

        //---------------------------------
        System.out.println("--------------------------");
        //3.2获取属性名为id的元素对象们
        Elements elements1 = document.getElementsByAttribute("id");
        System.out.println(elements1);

        System.out.println("------------------------------");
        //3.2获取number属性值为heima——0001的元素对象
        Elements elements2 = document.getElementsByAttributeValue("number", "heima_0001");
        System.out.println(elements2);

        System.out.println("-----------------------3333");
        //3.3获取id属性值的元素对象
        Element heima_0001 = document.getElementById("23");
        System.out.println(heima_0001);


    }
}
