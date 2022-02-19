package com.dilo.vodservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.dilo.baseservice.handler.DiloException;
import com.dilo.commonutils.ResultCode;
import com.dilo.vodservice.service.VideoService;
import com.dilo.vodservice.utils.VideoConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public String uploadVideoVOD(MultipartFile file) {

        String accessKeyId = VideoConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = VideoConstantPropertiesUtil.ACCESS_KEY_SECRET;

        try {
            InputStream inputStream = file.getInputStream();

            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            //获取请求对象
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId,accessKeySecret,title,fileName,inputStream);

            //获取客户端对象
            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            //客户端发送请求,获取响应对象
            UploadStreamResponse response = uploadVideo.uploadStream(request);

            String videoId = response.getVideoId();
            System.out.println(videoId);
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DiloException(ResultCode.ERROR,"视频上传失败");
        }
    }
}
