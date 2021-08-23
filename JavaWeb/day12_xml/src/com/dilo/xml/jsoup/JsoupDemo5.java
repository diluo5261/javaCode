package com.dilo.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


/*

选择器查询
 */
public class JsoupDemo5 {
    public static void main(String[] args) throws IOException {
        //1、获取Student。xml的路径
        String str = JsoupDemo5.class.getClassLoader().getResource("student.xml").getPath();

        //2、获取Document对象
        Document document = Jsoup.parse(new File(str),"UTF-8");

        //查询name标签
        Elements select = document.select("name");
        System.out.println(select);

        Elements elements = document.select("#23");
        System.out.println(elements);

        //5、获取student标签，并且number属性值为heima——0001的age子标签
        //5.1：
        System.out.println("777777777777777777");
        Elements select1 = document.select("student[number='heima_0001'] > age");
        System.out.println(select1);

    }
}
