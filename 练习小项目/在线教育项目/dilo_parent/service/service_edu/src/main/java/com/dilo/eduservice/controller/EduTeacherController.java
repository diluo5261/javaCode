package com.dilo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.R;
import com.dilo.eduservice.entity.EduTeacher;
import com.dilo.eduservice.entity.vo.TeacherQuery;
import com.dilo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@CrossOrigin//跨域注解
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping
    @ApiOperation("所有讲师列表")
    public R getAllTeacher(){
        List<EduTeacher> teacherList = eduTeacherService.list();
        try {
            int i = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DiloException(2001,"自定义异常!");
        }
        return R.ok().data("list",teacherList);
    }


    @ApiOperation("分页查询讲师信息")
    @GetMapping("getpage/{current}/{limit}")
    public R getTeacherPage(@PathVariable Long current, @PathVariable Long limit){
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page);
        List<EduTeacher> list = page.getRecords();;
        long total = page.getTotal();

        return R.ok().data("list",list).data("total",total);
    }

    @ApiOperation("带条件分页查询讲师信息")
    @PostMapping("getpageVo/{current}/{limit}")
    public R getTeacherPageVo(@PathVariable Long current,
                              @PathVariable Long limit,
                              @RequestBody TeacherQuery teacherQuery){
        //@RequestBody把json串转化成实体类
        //1.取出查询条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();;
        String end = teacherQuery.getEnd();


        //2.判断条件是否为空,不为空拼写sql
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
       wrapper.like(!StringUtils.isEmpty(name),"name",name).
               eq(!StringUtils.isEmpty(level),"level",level).
               ge(!StringUtils.isEmpty(begin),"gmt_create",begin).
               le(!StringUtils.isEmpty(end),"gmt_modified",end);


        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,wrapper);

        List<EduTeacher> list = page.getRecords();;
        long total = page.getTotal();

        return R.ok().data("list",list).data("total",total);
    }


    @ApiOperation("根据id删除讲师信息")
    @DeleteMapping("{id}")
    public R delTeacher( @PathVariable  String id){
        boolean remove = eduTeacherService.removeById(id);
        if (remove){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher){
        boolean save = eduTeacherService.save(teacher);;
        if (save){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("eduTeacher",teacher);
    }


    @ApiOperation("修改讲师的功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update){
            return R.ok();
        }else{
            return R.error();
        }

    }
}

