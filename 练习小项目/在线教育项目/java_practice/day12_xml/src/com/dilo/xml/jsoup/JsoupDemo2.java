package com.dilo.xml.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsoupDemo2 {
    public static void main(String[] args) throws IOException {
        //2.1、获取student。xml的path
        String path = JsoupDemo2.class.getClassLoader().getResource("student.xml").getPath();

        //2.2解析xml文档，加载文档进内存，获取dom树 -->Document
      /*  Document document = Jsoup.parse(new File(path), "utf-8");
        System.out.println(document);*/

        //2、parse(String html) : 解析xml 或 html
    /*    String str ="<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\n" +
                " <students>\n" +
                " \t<student number=\"heima_0001\">\n" +
                " \t\t<name>tom</name>\n" +
                " \t\t<age>18</age>\n" +
                " \t\t<sex>male</sex>\n" +
                " \t</student>\n" +
                "\n" +
                "\t<student number=\"heima_0002\">\n" +
                "\t\t<name>lisi</name>\n" +
                "\t\t<age>50</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\t\t \n" +
                " </students>";
        Document document = Jsoup.parse(str);
        System.out.println(document);*/

        //3、
        URL url = new URL("https://www.runoob.com/cprogramming/c-tutorial.html");
        Document document1 = Jsoup.parse(url,10000);
        System.out.println(document1);
    }
}
