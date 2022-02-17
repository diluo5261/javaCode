package com.dilo.eduservice.service;

import com.dilo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dilo.eduservice.entity.vo.CourseInfoForm;
import com.dilo.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishById(String id);

    void delCourseInfo(String id);
}
