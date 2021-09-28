package com.dilo.dao;

import com.dilo.domain.Student;

import java.util.List;

//接口操作student表
public interface StudentDao {

    //查询student表所有的数据
    public List<Student> selectStudents();
}
