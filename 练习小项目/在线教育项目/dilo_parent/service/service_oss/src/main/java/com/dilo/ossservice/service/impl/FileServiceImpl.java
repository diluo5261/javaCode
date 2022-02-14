package com.dilo.ossservice.service.impl;

import com.dilo.baseservice.handler.DiloException;
import com.dilo.ossservice.service.FileService;
import com.dilo.ossservice.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFileOSS(MultipartFile file) {
        //1.将配置相关的的信息提出到配置文件
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtil.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileName = file.getOriginalFilename();

        //2.优化文件名,放置重复
        fileName = UUID.randomUUID().toString()+fileName;

        //3.优化存储路径,(/2022/03/04/uuid+01.jpg)
        String path = new DateTime().toString("yyyy/MM/dd");

        fileName = path +"/"+fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        try {
            //上传文件流
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //https://dilo-guli.oss-cn-beijing.aliyuncs.com/01.jpeg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            //String url ="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;

        } catch (IOException e) {
            e.printStackTrace();
            throw new DiloException(20001,"上传失败");
        }



    }
}
