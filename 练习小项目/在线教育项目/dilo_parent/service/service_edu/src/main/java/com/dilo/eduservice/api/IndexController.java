package com.dilo.eduservice.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dilo.commonutils.R;
import com.dilo.eduservice.entity.EduCourse;
import com.dilo.eduservice.entity.EduTeacher;
import com.dilo.eduservice.service.EduCourseService;
import com.dilo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "首页显示课程/讲师")
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation("首页展示,8条课程,4条讲师")
    @GetMapping("/getCourseTeacherList")
    public R getCourseTeacherList(){

        //8条课程信息
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("gmt_create").last("limit 8");
        List<EduCourse> courseList = courseService.list(courseWrapper);

        //4条讲师信息
        QueryWrapper<EduTeacher> teacherQuery = new QueryWrapper<>();
        teacherQuery.orderByDesc("gmt_create").last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherQuery);

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
