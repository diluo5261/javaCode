package com.dilo.gmall.user.client.impl;

import com.dilo.gmall.model.user.UserAddress;
import com.dilo.gmall.user.client.UserFeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDegradeFeignClient implements UserFeignClient {
    @Override
    public List<UserAddress> findUserAddressListByUserId(String userId) {
        return null;
    }
}
