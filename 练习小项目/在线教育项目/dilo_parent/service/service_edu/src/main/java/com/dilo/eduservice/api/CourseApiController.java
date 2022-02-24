package com.dilo.eduservice.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dilo.commonutils.R;
import com.dilo.commonutils.utils.JwtUtils;
import com.dilo.commonutils.vo.CourseWebVoForOrder;
import com.dilo.eduservice.client.OrderClient;
import com.dilo.eduservice.entity.EduCourse;
import com.dilo.eduservice.entity.vo.ChapterVo;
import com.dilo.eduservice.entity.vo.CourseQueryVo;
import com.dilo.eduservice.entity.vo.CourseWebVo;
import com.dilo.eduservice.service.EduChapterService;
import com.dilo.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(description="前台课程展示")
@RestController
@RequestMapping("/eduservice/courseapi")
@CrossOrigin
public class CourseApiController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrderClient orderClient;

    @ApiOperation(value = "带条件分页查询课程列表")
    @PostMapping("getCourseApiPageVo/{current}/{limit}")
    public R getCourseApiPageVo(@PathVariable Long current,
                                @PathVariable Long limit,
                                @RequestBody CourseQueryVo courseQueryVo){
        Page<EduCourse> eduCoursePage = new Page<>();
        Map<String,Object> map = courseService.getCourseApiPageVo(eduCoursePage,courseQueryVo);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据课程id查询课程相关信息")
    @GetMapping("/getCourseWebInfo/{courseId}")
    public  R getCourseWebInfo(@PathVariable String courseId, HttpServletRequest request){
        //1.查询课程相关信息存入courseWebVo
        CourseWebVo courseWebVo = courseService.getCourseWebVo(courseId);

        //2.查询大纲信息
        List<ChapterVo> chapterVoList = chapterService.getChapterVideoById(courseId);

        //3.根据课程id,用户id查询用户是否已经购买,远程调用
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean isBuyCourse= orderClient.isBuyCourse(courseId, memberId);


        return R.ok().data("courseWebVo",courseWebVo).data("chapterVoList",chapterVoList).data("isBuyCourse",isBuyCourse);
    }

    @ApiOperation("根据课程id查询课程相关信息跨模块")
    @GetMapping("/getCourseInfoForOrder/{courseId}")
    public CourseWebVoForOrder getCourseInfoForOrder(@PathVariable("courseId") String courseId){
        CourseWebVo courseWebVo = courseService.getCourseWebVo(courseId);
        CourseWebVoForOrder courseWebVoForOrder = new CourseWebVoForOrder();
        BeanUtils.copyProperties(courseWebVo,courseWebVoForOrder);
        return courseWebVoForOrder;

    }

}
