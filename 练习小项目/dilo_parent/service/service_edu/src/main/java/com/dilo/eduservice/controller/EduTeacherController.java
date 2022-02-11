package com.dilo.eduservice.controller;


import com.dilo.eduservice.entity.EduTeacher;
import com.dilo.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-11
 */
@RestController
@RequestMapping("/eduservice/eduteacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping
    public List<EduTeacher> getAllTeacher(){
        List<EduTeacher> teacherList = eduTeacherService.list();
        return teacherList;
    }

    @DeleteMapping("{id}")
    public boolean delTeacher( @PathVariable  String id){
        boolean remove = eduTeacherService.removeById(id);
        return remove;
    }

}

