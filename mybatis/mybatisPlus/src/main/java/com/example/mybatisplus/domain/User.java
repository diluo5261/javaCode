package com.example.mybatisplus.domain;



import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class User extends Model<User> {

    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private int age;
    private String email;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer deleted;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

    //插入数据时填充
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //插入数据,更新数据填充
    @TableField(fill =FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
