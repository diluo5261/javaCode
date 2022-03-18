package com.dilo.gmall.user.service;

import com.dilo.gmall.model.user.UserAddress;

import java.util.List;

public interface UserAddressService {
    //根据id查询收获地址列表
    List<UserAddress> findUserAddressListByUserId(String userId);
}
