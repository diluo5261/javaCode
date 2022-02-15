package com.dilo.eduservice.controller;


import com.dilo.commonutils.R;
import com.dilo.eduservice.entity.vo.CourseInfoForm;
import com.dilo.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-15
 */
@Api("课程管理")
@RestController
@RequestMapping("/eduservice/educourse")
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    @ApiModelProperty("添加课程信息")
    @PostMapping("/addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){
        courseService.addCourseInfo(courseInfoForm);

        return R.ok();
    }

}

