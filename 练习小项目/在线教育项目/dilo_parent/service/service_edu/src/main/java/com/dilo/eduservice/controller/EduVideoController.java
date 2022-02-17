package com.dilo.eduservice.controller;


import com.dilo.commonutils.R;
import com.dilo.eduservice.entity.EduVideo;
import com.dilo.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author dilo
 * @since 2022-02-16
 */

@Api(description = "章节管理")
@RestController
@RequestMapping("/eduservice/eduvideo")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @ApiOperation("添加小节")
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    @ApiOperation("根据id删除小节")
    @DeleteMapping("delVideo/{id}")
    public R delVideo(@PathVariable String id){
        videoService.removeById(id);

    //TODO 删除小节的同时删除阿里云视频
        return R.ok();
    }

    @ApiOperation("根据id查询小节信息")
    @GetMapping("/getVideoById/{id}")
    public R getVideoById(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        return R.ok().data("eduVideo",eduVideo);
    }

    @ApiOperation("修改小节")
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }


}

