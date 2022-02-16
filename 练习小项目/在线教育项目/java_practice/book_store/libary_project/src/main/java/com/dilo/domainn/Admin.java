package com.dilo.domainn;

import com.dilo.service.*;
import com.dilo.service.impl.*;


import java.util.Scanner;

//管理员
public class Admin extends User{

    public Admin() {
        this.operations=new IOperation[]{

                new ExitOperation(),
                new DisplayOperation(),
                new FindOperation(),
                new AddOperaton(),
                new DelOperation()
        };

    }

    @Override
    public int menu() {

        //打印普通用户的菜单项
        System.out.println("======================");
        System.out.println("welcome," + getUsername()+"管理员!");
        System.out.println("======1、查看列表====");
        System.out.println("======2、查找书籍====");
        System.out.println("======3、新增书籍====");
        System.out.println("======4、删除书籍====");
        System.out.println("======0、退出系统====");
        System.out.println("======================");

        System.out.println("请输入选项：");
        int choice = new Scanner(System.in).nextInt();


        return choice;
    }
}
