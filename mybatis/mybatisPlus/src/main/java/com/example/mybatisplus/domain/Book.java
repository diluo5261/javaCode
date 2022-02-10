package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class Book {
    /*
    指定主键
    value:主键字段的名称,如果是id,可以不用写
    type:指定主键的类型,主键的值如何生成,idType.AUTO表示自动增长
     */

    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    private String name;
    private double price;
    private String author;
}
