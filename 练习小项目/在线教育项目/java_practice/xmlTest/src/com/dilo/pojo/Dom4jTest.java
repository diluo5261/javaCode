package com.dilo.pojo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jTest {

    @Test
    public void test1() throws DocumentException {
        //1. 要创建一个Document对象,需要我们先创建一个SAXReader 输入流对象
        SAXReader saxReader = new SAXReader();

        //2. 用这个对象读取xml文件,然后返回一个Document对象
        Document document = saxReader.read("src/books.xml");

        System.out.println(document);
    }

    @Test
    public void test2() throws DocumentException {
        //1. 读取books.xml对象
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/books.xml");
        //2.通过Document对象获取根元素
        Element root = document.getRootElement();

        //Element.asXML()  将当前元素转化为 String 对象
//        System.out.println(root.asXML());


        //3.通过根元素获取book标签对象
        List<Element> books = root.elements();

        //4.遍历,处理每个book标签转换为Book类
        for (Element book:books ) {
//            System.out.println(book.asXML());
            //拿到每个book下面的元素对象
            Element nameElement = book.element("name");
            //通过getText():获取标签中的文本内容
            String name = nameElement.getText();

            String price = book.elementText("price");
            String author = book.elementText("author");
            String sn = book.attributeValue("sn");
            Book book1 = new Book(sn, name, Double.parseDouble(price),author);
            System.out.println(book1);

        }
    }
}
