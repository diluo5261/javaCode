package com.dilo.eduservice.service.impl;

import com.dilo.baseservice.handler.DiloException;
import com.dilo.eduservice.entity.EduCourse;
import com.dilo.eduservice.entity.EduCourseDescription;
import com.dilo.eduservice.entity.vo.CourseInfoForm;
import com.dilo.eduservice.mapper.EduCourseDescriptionMapper;
import com.dilo.eduservice.mapper.EduCourseMapper;
import com.dilo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseMapper courseMapper;

    @Autowired
    private EduCourseDescriptionMapper descriptionMapper;

    @Override
    public void addCourseInfo(CourseInfoForm courseInfoForm) {
    //    1.添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int res = courseMapper.insert(eduCourse);

        if (res == 0){
            throw new DiloException(20001,"创建课程失败!");
        }

        //2.获取课程id
        String courseId = eduCourse.getId();
        String des = courseInfoForm.getDescription();
        //3.添加课程描述信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseId);
        courseDescription.setDescription(des);

        descriptionMapper.insert(courseDescription);
    }
}
