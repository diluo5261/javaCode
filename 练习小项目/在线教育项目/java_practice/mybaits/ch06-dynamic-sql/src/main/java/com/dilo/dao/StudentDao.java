package com.dilo.dao;

import com.dilo.domain.Student;
import com.dilo.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

   //动态sql , 使用java对象作为参数
   List<Student> selectStudentIf(Student student);

   //where使用
   List<Student> selectStudentWhere(Student student);
}
