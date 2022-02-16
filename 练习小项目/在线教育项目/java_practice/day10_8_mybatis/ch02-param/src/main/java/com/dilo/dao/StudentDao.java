package com.dilo.dao;

import com.dilo.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDao {

    /**
     * 简单类型的参数:mybatis中把String 和基本数据类型称为简单参数
     * 在 mapper文件中获取简单类型参数的值 使用 #{任意字符}
     * @param id
     * @return
     */
    Student selectStudentById(Integer id);

    /**
     * 多个参数 使用 @Param{"自定义形参名"}
     * mapper文件中使用 #{自定义形参名}
     * @param name
     * @param age
     * @return
     */
    List<Student> selectStudentMultiParam(@Param("myName") String name, @Param("myAge") Integer age);


//    通过对象传参
    List<Student> selectStudentByObject(Student student);

    List<Student> selectStudentMultiParamByOrder(String name,Integer age);


   /* //查询所有的数据
    List<Student> selectAll();

    //插入数据
    int insertStudent(Student student);

    //更新数据
    int updateStudent(Student student);

    //删除数据
    int deleteStudent(Integer id);*/

}
