package com.dilo.xml.jsoup;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;


/*

XPAth查询
 */
public class JsoupDemo6 {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        //1、获取Student。xml的路径
        String str = JsoupDemo6.class.getClassLoader().getResource("student.xml").getPath();

        //2、获取Document对象
        Document document = Jsoup.parse(new File(str),"UTF-8");

        //根据document对象，创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);

        //4结合xPath语法查询
        //查询所有的student标签
        List<JXNode> jxNodes =jxDocument.selN("//student");

//        查询所有student标签下的name标签
        List<JXNode> jxNodes1 =jxDocument.selN("//student/name");

        //查询student标签下带有id属性的name标签
        List<JXNode> jxNodes3 =jxDocument.selN("//student/name[@id]");
        for (JXNode jxNode: jxNodes3 ) {
            System.out.println(jxNode);

        }

        System.out.println("***********************");
        //4.4查询student标签下带有id属性的name标签，并且id属性值为23
        List<JXNode> jxNodes4 =jxDocument.selN("//student/name[@id = '23']");
        for (JXNode jxNode: jxNodes4 ) {
            System.out.println(jxNode);

        }


    }
}
