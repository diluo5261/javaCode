package com.dilo.gmall.product.controller;

import com.dilo.gmall.common.result.Result;
import io.swagger.annotations.Api;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "文件上传")
@RestController
@RequestMapping("admin/product/")
public class FileUploadController {

    @Value("${fileServer.url}")
    private String fileUrl;
    //再后台管理组件vue中,给的名字是file

    @PostMapping("fileUpload")
    public Result<String> fileUpload(MultipartFile file) throws Exception {
        //1.加载配置文件
        String configFile = this.getClass().getResource("/tracker.conf").getFile();
        System.out.println("获取文件的路径" + configFile);
        System.out.println("file文件路径" + file);

        String path = null;


        //2.初始化当前文件

        if (configFile != null) {


            ClientGlobal.init(configFile);

            //3.创建TrackerClient
            TrackerClient trackerClient = new TrackerClient();

            //4.创建TrackerServer
            TrackerServer trackerServer = trackerClient.getConnection();

            //5.创建StorageClient1
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);

            //6.文件上传
            path = storageClient1.upload_appender_file1(file.getBytes(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
            System.out.println("文件上传之后的路径:" + path);

        }

        return Result.ok(fileUrl + path);
    }

}
