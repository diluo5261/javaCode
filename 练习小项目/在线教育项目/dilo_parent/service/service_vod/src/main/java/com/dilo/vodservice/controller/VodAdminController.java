package com.dilo.vodservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.R;

import com.dilo.commonutils.ResultCode;
import com.dilo.vodservice.service.VideoService;

import com.dilo.vodservice.utils.AliyunVodSDKUtils;
import com.dilo.vodservice.utils.VideoConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(description="视频管理")
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodAdminController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("/uploadVideo")
    public R uploadVideo(MultipartFile file){
        String videoId = videoService.uploadVideoVOD(file);
        System.out.println("vod_controller:"+videoId);

        return R.ok().data("videoId",videoId);
    }

    @ApiOperation(value = "删除视频")
    @DeleteMapping("delVideo/{videoId}")
    public R delVideo(@PathVariable String videoId){
        
        try {
            //1.创建客户端对象
            String accessKeyId = VideoConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = VideoConstantPropertiesUtil.ACCESS_KEY_SECRET;

            DefaultAcsClient client =
                    AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);

            //2.创建请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //3.设置请求参数
            request.setVideoIds(videoId);

            //4.使用客户端对象发送请求,获取响应对象
            client.getAcsResponse(request);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new DiloException(ResultCode.ERROR,"删除视频失败");
        }
    }

    @ApiOperation(value = "批量删除视频")
    @DeleteMapping("delVideoList")
    public R delVideoList(@RequestParam("videoList") List<String> videoList){

        try {
            //1.创建客户端对象
            String accessKeyId = VideoConstantPropertiesUtil.ACCESS_KEY_ID;
            String accessKeySecret = VideoConstantPropertiesUtil.ACCESS_KEY_SECRET;

            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);

            //2.创建请求对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            String videos = StringUtils.join(videoList.toArray(),",");

            //3.设置请求参数
            request.setVideoIds(videos);

            //4.使用客户端对象发送请求,获取响应对象
            client.getAcsResponse(request);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw  new DiloException(ResultCode.ERROR,"批量删除视频失败");
        }
    }
}
