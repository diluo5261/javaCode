package com.dilo;

import com.dilo.dao.UserDao;
import com.dilo.domainn.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void selectTest(){
        UserDao userDao = new UserDao();
        User user = userDao.selectByName("admin");

        if (user != null){
            System.out.println(user);
        }
    }
}
