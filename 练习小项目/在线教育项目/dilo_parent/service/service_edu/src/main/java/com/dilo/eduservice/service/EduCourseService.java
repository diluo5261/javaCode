package com.dilo.eduservice.service;

import com.dilo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.eduservice.entity.vo.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
public interface EduCourseService extends IService<EduCourse> {

    void addCourseInfo(CourseInfoForm courseInfoForm);
}
