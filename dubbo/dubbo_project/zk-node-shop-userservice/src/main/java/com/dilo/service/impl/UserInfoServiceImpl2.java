package com.dilo.service.impl;

import com.dilo.domain.Address;
import com.dilo.service.UserInfoService;

import java.util.ArrayList;
import java.util.List;

public class UserInfoServiceImpl2 implements UserInfoService {

    @Override
    public List<Address> queryAddress(Integer id) {
        List<Address> addressList = new ArrayList<>();
        //根据userid给出不同的地址
        if (id == 1){
            Address address = new Address();
            address.setCity("北京2222");
            address.setName("张三2222");
            address.setStreet("前门外大街222");
            address.setZipcode("10010");
            address.setMobile("13562358865");
            address.setUse(true);
            addressList.add(address);

            address = new Address();
            address.setCity("北京111");
            address.setName("张三111");
            address.setStreet("前门外大街111");
            address.setZipcode("10010111");
            address.setMobile("13562358865111");
            address.setUse(false);
            addressList.add(address);



        }
        return addressList;
    }
}
