package com.dilo.service;

import com.dilo.domain.Address;

import java.util.List;

public interface UserInfoService {
    List<Address> queryAddress(Integer id);
}
