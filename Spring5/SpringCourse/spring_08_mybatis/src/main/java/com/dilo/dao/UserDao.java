package com.dilo.dao;

import com.dilo.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserDao {
    List<User> selectStudents();

    User selectOne(int id);

    int insert(User user);

    int update(User user);

    int delete(int id);

    List<User> selectMultiParam(@Param("personName") String name, @Param("personAge") int age);
    List<User> selectMultiObject(User user);

    List<User> selectMultiPos(String name,int age);

    List<User> selectMultiMap(HashMap<String,Object> hashMap);

    public List<User> selectUserByNick(@Param("keyWord") String keyword);

    User findByDiffFiled(@Param("col") String colName,@Param("cval") Object value);

    List<User> selectLikeFirst(String name);

}
