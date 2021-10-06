package com.dilo.domainn;

/*
针对此处的 isAdmin , 并不是直接使用一个 boolean 变量来区分,而是使用两个不同的子类
原因是,管理员和普通用户,支持的方法是不一样的
 */

import com.dilo.service.IOperation;

public abstract class User {
    private Integer userId;
    private String username;
    private String password;

    /*
    包含一个数组,数组里面就是该用户支持哪些操作
    针对普通用户, 和管理员,分别设置不同的操作
     */


    protected IOperation[] operations;

    //普通用户和管理员看到的菜单也不同
    abstract  public  int menu();

    public void doOperation(int choice){
        operations[choice].work();
    }



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IOperation[] getOperations() {
        return operations;
    }

    public void setOperations(IOperation[] operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
