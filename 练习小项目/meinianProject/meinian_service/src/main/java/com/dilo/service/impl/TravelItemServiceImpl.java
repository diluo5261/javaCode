package com.dilo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dilo.dao.TravelItemDao;
import com.dilo.service.TravelItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Service(interfaceClass = TravelItemService.class)//发布服务,注册到zk中心
@Transactional //声明式事务
public class TravelItemServiceImpl implements TravelItemService {

    @Autowired
    TravelItemDao travelItemDao;


}
