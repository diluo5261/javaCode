package com.dilo.dao;

import com.dilo.domain.Student;
import com.dilo.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {

   /**
    * 一个简单类型的参数
    * 简单类型:mybatis 把 java的基本数据类型和String 都叫简单类型
    *
    * 在mapper文件获取简单类型的一个参数的指,使用 #{任意字符}
    *
    * @param id
    * @return
    */
   Student selectStudentById(Integer id);


//   多个参数,命名参数,在形参定义的前面加入 @Param("自定义参数名称")

   List<Student> selectMultiParam(@Param("myname") String name , @Param("myage") Integer age);


   int countStudent();

   //定义方法返回 Map
   Map<Object,Object> selectMapById(Integer id);


   /*
   使用resultMap定义映射关系

    */

   List<Student> selectAllStudents();

   List<Student> selectDiffColProperty();

   //第一种模糊查询
   //        在java代码中指定like的内容
   List<Student> selectLikeOne(String name);
}
