package com.dilo.service.impl;

import com.dilo.mapper.AdminMapper;
import com.dilo.pojo.Admin;
import com.dilo.pojo.AdminExample;
import com.dilo.service.AdminService;
import com.dilo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//把当前业务层对象的创建交给框架
@Service
public class AdminServiceImpl implements AdminService {

    //在业务逻辑层中,一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String name, String pwd) {

        //根据传入的用户名到 DB中查询响应的用户对象
        //如果有条件,一定要创建AdminExample的对象,用来封装条件
        AdminExample example = new AdminExample();

        /*
        如何添加条件
        select * from admin where a_name = 'admin'
         */

        //添加用户名 a_name 条件
        example.createCriteria().andANameEqualTo(name);

        //用这个条件完成查询
        List<Admin> list = adminMapper.selectByExample(example);
        if (list.size() >0){
            //将密码转换成密文
            //String pwdMd5 = MD5Util.getMD5(pwd);
            Admin admin = list.get(0);
            if (admin.getaPass().equals(pwd)){
                return admin;
            }
        }
        //如果查询到用户对象,再进行密码的比对
        return null;
    }
}
