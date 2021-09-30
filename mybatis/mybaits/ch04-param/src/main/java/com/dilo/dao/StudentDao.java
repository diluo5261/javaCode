package com.dilo.dao;

import com.dilo.domain.Student;
import com.dilo.vo.QueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

//多个参数,使用java对象作为家口中方法的参数

   List<Student> selectMultiObject(QueryParam param); //类型无限制

   List<Student> selectMultiStudent(Student student);

}
