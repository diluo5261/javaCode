package com.dilo.service;

import com.dilo.model.Student;

public interface StudentService {

    /**
     * 根据学生 ID 查询学生信息
     * @param id
     * @return
     */
    Student queryStudentById(Integer id);
}
