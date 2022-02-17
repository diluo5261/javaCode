package com.dilo.eduservice.mapper;

import com.dilo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dilo.eduservice.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
@Mapper
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCoursePublishById(String id);
}
