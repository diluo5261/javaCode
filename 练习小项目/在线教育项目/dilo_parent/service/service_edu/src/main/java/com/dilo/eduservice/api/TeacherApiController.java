package com.dilo.eduservice.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.commonutils.R;
import com.dilo.eduservice.entity.EduCourse;
import com.dilo.eduservice.entity.EduTeacher;
import com.dilo.eduservice.service.EduCourseService;
import com.dilo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(description = "前台讲师展示")
@RestController
@RequestMapping("/eduservice/teacerapi")
@CrossOrigin
public class TeacherApiController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @ApiOperation("分页查询讲师信息")
    @GetMapping("getTeacherApiPage/{current}/{limit}")
    public R getTeacherApiPage(@PathVariable Long current, @PathVariable Long limit){

        Page<EduTeacher> page = new Page<>(current,limit);
       Map<String ,Object> map = teacherService.getTeacherApiPage(page);
        return R.ok().data(map);
    }

    @ApiOperation("前台查询讲师详情")
    @GetMapping("getTeacherCourseById/{id}")
    public R getTeacherCourseById(@PathVariable String id){
        //1.讲师信息
        EduTeacher eduTeacher = teacherService.getById(id);

        //2.查询相关课程
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);

        List<EduCourse> courseList = courseService.list(queryWrapper);


        return  R.ok().data("courseList",courseList).data("eduTeacher",eduTeacher);
    }
}
