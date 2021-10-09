package com.dilo.dao;

import com.dilo.domain.Student;

import java.util.List;

public interface StudentDao {
    //查询所有的数据
    List<Student> selectAll();

    //插入数据
    int insertStudent(Student student );

    //更新数据
    int updateStudent(Student student);

    //删除数据
    int deleteStudent(Integer id);

}
