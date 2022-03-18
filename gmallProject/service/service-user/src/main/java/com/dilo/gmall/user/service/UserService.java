package com.dilo.gmall.user.service;

import com.dilo.gmall.model.user.UserInfo;

public interface UserService {
    /**
     * 登录方法
     * @param userInfo
     * @return
     */
    UserInfo login(UserInfo userInfo);
}
