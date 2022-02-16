package com.dilo.domainn;

import com.dilo.service.*;
import com.dilo.service.impl.*;


import java.util.Scanner;

public class NormalUser extends User{
    public NormalUser() {
        DisplayOperation displayOperation = new DisplayOperation();
        this.operations=new IOperation[]{
                new ExitOperation(),
                new DisplayOperation(),
                new FindOperation(),
                new BorrowOperation(),
                new ReturnOperation()
        };
    }

    @Override
    public int menu() {
        //打印普通用户的菜单项
        System.out.println("======================");
        System.out.println("welcome," + getUsername()+"!");
        System.out.println("======1、查看列表====");
        System.out.println("======2、查找书籍====");
        System.out.println("======3、借阅书籍====");
        System.out.println("======4、归还书籍====");
        System.out.println("======0、退出系统====");
        System.out.println("======================");

        System.out.println("请输入选项：");
        int choice = new Scanner(System.in).nextInt();


        return choice;
    }
}
