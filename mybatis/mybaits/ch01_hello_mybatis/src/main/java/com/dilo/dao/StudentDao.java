package com.dilo.dao;

import com.dilo.domain.Student;

import java.util.List;

//接口操作student表
public interface StudentDao {

    //查询student表所有的数据
    public List<Student> selectStudents();

    //插入方法

    /**
     * 插入方法
     * @param student 表示要插入数据看的数据
     * @return  表示执行insert 操作后,影响数据的行数
     */
    public int insertStudent(Student student);
}
