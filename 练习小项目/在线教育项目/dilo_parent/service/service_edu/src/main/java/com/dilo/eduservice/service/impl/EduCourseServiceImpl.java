package com.dilo.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.ResultCode;
import com.dilo.eduservice.entity.EduChapter;
import com.dilo.eduservice.entity.EduCourse;
import com.dilo.eduservice.entity.EduCourseDescription;
import com.dilo.eduservice.entity.EduVideo;
import com.dilo.eduservice.entity.vo.CourseInfoForm;
import com.dilo.eduservice.entity.vo.CoursePublishVo;
import com.dilo.eduservice.mapper.EduChapterMapper;
import com.dilo.eduservice.mapper.EduCourseDescriptionMapper;
import com.dilo.eduservice.mapper.EduCourseMapper;
import com.dilo.eduservice.mapper.EduVideoMapper;
import com.dilo.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

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
    @Autowired
    private EduChapterMapper chapterMapper;

    @Autowired
    private EduVideoMapper videoMapper;

    @Autowired
    private EduCourseDescriptionMapper courseDescriptionMapper;

    @Override
    public String addCourseInfo(CourseInfoForm courseInfoForm) {
    //    1.添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        //int res = courseMapper.insert(eduCourse);
        int res = baseMapper.insert(eduCourse);

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
        return courseId;
    }

    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //1.根据id查询课程
        EduCourse eduCourse = courseMapper.selectById(id);

        //2.封装信息
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);

        //3.根据id查询描述信息
        EduCourseDescription description = descriptionMapper.selectById(id);
        //4.封装课程信息
        courseInfoForm.setDescription(description.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //1.复制课程数据
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);

        //2.跟新课程数据

        int res = courseMapper.updateById(eduCourse);


        //3.判断是否成功
        if (res != 1){
            throw new DiloException(ResultCode.ERROR,"课程更新失败");
        }

        //4.更新课程描述
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoForm.getId());
        description.setDescription(courseInfoForm.getDescription());

        descriptionMapper.updateById(description);
    }

    //根据id查询课程信息
    @Override
    public CoursePublishVo getCoursePublishById(String id) {
        CoursePublishVo coursePublishVo = courseMapper.getCoursePublishById(id);
        return coursePublishVo;
    }

    @Override
    public void delCourseInfo(String id) {
        //1.删除视频
        //TODO

        //2.删除小节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",id);
        videoMapper.delete(videoWrapper);

        //3.删除章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        chapterMapper.delete(chapterWrapper);

        //4.删除课程描述
        courseDescriptionMapper.deleteById(id);

        //5.删除课程
        int delete = courseMapper.deleteById(id);

        if (delete != 1){
            throw new DiloException(ResultCode.ERROR,"删除课程失败");
        }

    }


}
