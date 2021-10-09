package com.dilo;

import com.dilo.dao.StudentDao;
import com.dilo.domain.Student;
import com.dilo.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestByGetMapper {

    @Test
    public void selectTest(){
        SqlSession session = MyBatisUtils.getSqlSession();

        StudentDao dao = session.getMapper(StudentDao.class);

        List<Student> studentList = dao.selectAll();

        for (Student student : studentList){
            System.out.println(student);
        }
        session.close();
    }
}
