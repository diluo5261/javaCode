package com.dilo.gmall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.gmall.model.user.UserInfo;
import com.dilo.gmall.user.mapper.UserInfoMapper;
import com.dilo.gmall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo login(UserInfo userInfo) {
        //select * from userInfo where userName= ? and passwd = ?
        String passwd = userInfo.getPasswd();
        String newPasswd = DigestUtils.md5DigestAsHex(passwd.getBytes());
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name",userInfo.getLoginName()).eq("passwd",newPasswd);
        UserInfo info = userInfoMapper.selectOne(queryWrapper);
        return info;
    }
}
