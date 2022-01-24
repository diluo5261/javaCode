package com.dilo.factory;

import com.dilo.dao.UserDao;
import com.dilo.dao.impl.UserDaoImpl;

public class DynamicFactory {
    public UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
