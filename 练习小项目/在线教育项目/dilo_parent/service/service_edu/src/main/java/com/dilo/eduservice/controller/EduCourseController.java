package com.dilo.eduservice.controller;


import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.R;
import com.dilo.commonutils.ResultCode;
import com.dilo.eduservice.entity.EduCourse;
import com.dilo.eduservice.entity.EduCourseDescription;
import com.dilo.eduservice.entity.vo.CourseInfoForm;
import com.dilo.eduservice.entity.vo.CoursePublishVo;
import com.dilo.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/educourse")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiOperation(value = "添加课程信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.addCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId);
    }

    @ApiOperation(value = "根据id查询课程信息")
    @GetMapping("getCourseInfoById/{id}")
    public R getCourseInfoById(@PathVariable String id){

        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        return R.ok().data("courseInfo",courseInfoForm);
    }

    @ApiOperation(value = "根据id修改课程信息课程信息")
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        courseService.updateCourseInfo(courseInfoForm);
        return R.ok();
    }

    @ApiOperation("根据课程id查询课程发布信息")
    @GetMapping("/getCoursePublishById/{id}")
    public R getCoursePublishById(@PathVariable String id){

        CoursePublishVo coursePublishVo = courseService.getCoursePublishById(id);
        return R.ok().data("coursePublishVo",coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable  String id){
        EduCourse eduCourse = courseService.getById(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation("查询所有课程信息")
    @GetMapping("getCourseInfo")
    public R getCourseInfo(){
        List<EduCourse> courseList = courseService.list();
        return R.ok().data("courseList",courseList);
    }

    @ApiOperation("根据id删除课程信息")
    @DeleteMapping("delCourseInfo/{id}")
    public R delCourseInfo(@PathVariable String id){
        courseService.delCourseInfo(id);
        return R.ok();
    }




}

