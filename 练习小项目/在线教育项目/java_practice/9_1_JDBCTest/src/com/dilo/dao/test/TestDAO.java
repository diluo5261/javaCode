package com.dilo.dao.test;

import com.dilo.dao.dao.ActorDAO.ActorDAO;
import com.dilo.dao.domain.Actor;

import java.sql.SQLException;
import java.util.List;

public class TestDAO {
    //测试ActorDAO 对actor表得crud操作
    public void testActorTest() throws SQLException {

        ActorDAO actorDAO = new ActorDAO();
        //1. 查询
        List<Actor> actors = actorDAO.queryMulti("select * from acrot where id >= ?", Actor.class, 1);
        System.out.println("查询结果:");
        for (Actor actor : actors){
            System.out.println(actor);
        }

        //2. 查询单行记录
        Actor actor = actorDAO.querySingle("select * form actor where id = ?", Actor.class, 1);
        System.out.println("查询单行结果:");
        System.out.println(actor);

        //3. 查询单行单列
        Object o = actorDAO.queryScalar("select name form actor where id = ?", 6);
        System.out.println(o);

        //4. dml操作
        int update = actorDAO.update("insert into actor values(null,?,?,?<?)", "王五", "男", "2020-02-03");
        System.out.println("受影响行数:" + update);


    }
}
