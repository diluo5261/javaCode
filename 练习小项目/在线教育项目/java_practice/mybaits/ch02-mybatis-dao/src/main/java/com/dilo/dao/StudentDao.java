package com.dilo.dao;

import com.dilo.domain.Student;

import java.util.List;

public interface StudentDao {

    /**
     * 查询语句
     * @return
     */
    List<Student>  selectStudent();

    int insertStudent(Student student);




}
