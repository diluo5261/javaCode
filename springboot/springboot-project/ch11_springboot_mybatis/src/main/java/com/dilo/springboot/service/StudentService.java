package com.dilo.springboot.service;

import com.dilo.springboot.model.Student;

public interface StudentService {
    /**
     * 根据学生 id 查询学生信息
     * @param id
     * @return
     */
    public Student queryStudentById(Integer id);
}
