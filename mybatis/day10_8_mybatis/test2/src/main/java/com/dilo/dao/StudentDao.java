package com.dilo.dao;

import com.dilo.domain.Student;

import java.util.List;

public interface StudentDao {

    //查找数据库所有的数据
    List<Student> selectAll();
}
