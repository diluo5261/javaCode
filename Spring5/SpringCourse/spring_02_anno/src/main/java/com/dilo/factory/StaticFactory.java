package com.dilo.factory;

import com.dilo.dao.UserDao;
import com.dilo.dao.impl.UserDaoImpl;

public class StaticFactory {

    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }
}
