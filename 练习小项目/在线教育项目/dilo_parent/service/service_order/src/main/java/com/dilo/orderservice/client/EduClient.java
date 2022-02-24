package com.dilo.orderservice.client;

import com.dilo.commonutils.vo.CourseWebVoForOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-edu")
public interface EduClient {
    //根据课程id查询课程相关信息,跨模块
    @GetMapping("/eduservice/courseapi/getCourseInfoForOrder/{courseId}")
    public CourseWebVoForOrder getCourseInfoForOrder(@PathVariable("courseId") String courseId);
}
