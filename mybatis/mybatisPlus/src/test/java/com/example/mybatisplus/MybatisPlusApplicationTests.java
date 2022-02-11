package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.dao.UserDao;
import com.example.mybatisplus.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserDao userDao;

    //查询所有
    @Test
    void getAll() {
        List<User> users = userDao.selectList(null);
        for (User user : users) {
            System.out.println(user);

        }
    }

    //分局id查询
    @Test
    void testSelectById(){
        User user = userDao.selectById(7);
        System.out.println(user);
    }

    //根据集合查询
    @Test
    void testSelectByIds(){
        List<User> userList = userDao.selectBatchIds(Arrays.asList(1, 3, 5, 7));
        for (User user : userList) {
            System.out.println(user);
        }
    }


    //简单条件查询
    @Test
    void  testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","哆啦A梦");
        map.put("age",26);

        List<User> users = userDao.selectByMap(map);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //查询一个
    @Test
    void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","tom");
        User user = userDao.selectOne(wrapper);
        System.out.println(user);

    }

    //分页查询
    @Test
    void testSelectByPage(){
        Page<User> userPage = new Page<>(1,2);
        userDao.selectPage(userPage,null);

        List<User> userList = userPage.getRecords();
        for (User user : userList) {
            System.out.println(user);
        }

        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getPages());
        System.out.println(userPage.getSize());
        System.out.println(userPage.getTotal());
        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }



    //根据id删除
    @Test
    void testDeleteById(){
        int id = 8;
        int res = userDao.deleteById(id);
        System.out.println(res>0?"id:"+id+"删除成功!":"删除失败!");
    }


    //批量删除
    @Test
    void testDelUsers(){
        int res = userDao.deleteBatchIds(Arrays.asList(10, 11,12));
        System.out.println(res);

    }

    //逻辑删除
    @Test
    void testDelByLogic(){
        User user = new User();

        boolean deleteById = user.deleteById(23);
        System.out.println(deleteById);
    }

    //插入数据
    @Test
    void testInsert(){

        for (int i = 0; i < 10; i++) {
            User user = new User();
            //user.setId(66);
            user.setName("哆啦A梦");
            user.setAge(26);
            user.setEmail("163543@qq.com");

            int res = userDao.insert(user);
            System.out.println(res);
            System.out.println(user);
        }
    }


    //根据id更新
    @Test
    void testUpdate(){
        User user = new User();
        user.setId(8);
        user.setName("大雄");
        user.setAge(888);

        int res = userDao.updateById(user);
        System.out.println(res);
        System.out.println(user);

    }

    //条件更新
    @Test
    void  testUpdateByQuery(){
        User user = new User();
        user.setDeleted(0);
        user.setVersion(0);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","哆啦A梦");

        System.out.println(user);
        int res = userDao.update(user,wrapper);
        System.out.println(res);
        System.out.println(user);
    }

    @Test
    void testUpdateLogic(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","哆啦A梦");
        List<User> userList = userDao.selectList(wrapper);
        for (User user : userList) {
            user.setAge(99);
            userDao.updateById(user);
        }
    }



}
