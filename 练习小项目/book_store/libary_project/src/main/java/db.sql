
create database if not exists bookmanager;


drop table book if exists book
create table book(
     bookid int primary key auto_increment,
     name varchar(20),
     author varchar(20),
     price int(10),
     type varchar(20),
     isBorrowed int(10) -- 如果 isBorrowed 为1 ,表示已经借出 , 为 0 表示 表示未借出
);

create table if not exists user(
    userId int primary key auto_increment,
    username varchar(20),
    password varchar(20),
    -- 如果 isAdmin 为1 ,表示是管理员 , 为 0 表示 是普通用户
    isAdmin int

)