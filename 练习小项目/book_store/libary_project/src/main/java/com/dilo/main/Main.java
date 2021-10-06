package com.dilo.main;

import com.dilo.dao.UserDao;
import com.dilo.domainn.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //通过login方法进行用户登录
        User user = login();
        while(true){
            int choice = user.menu();
            user.doOperation(choice);
        }
        
    }

    private static User login() {
        //让用户名输入用户名和密码
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入用户名:");
        String username = scan.nextLine();
        System.out.print("请输入密码:");
        String password = scan.nextLine();

        //2.从数据库根据用户名获取查询密码
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(username);

        if (user == null){
            System.out.println("登录失败!");
            //退出程序
            System.exit(0);
        }

        if (!password.equals(user.getPassword())){
            System.out.println("密码错误!");
            System.exit(0);
        }
        return user;
    }
}
