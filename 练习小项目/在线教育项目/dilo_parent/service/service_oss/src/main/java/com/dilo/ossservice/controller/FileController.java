package com.dilo.ossservice.controller;

import com.dilo.commonutils.R;
import com.dilo.ossservice.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "文件管理")
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin//跨域注解
public class FileController {

    @Autowired
    private FileService fileService;

    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public R uploadFile(MultipartFile file){
        String url = fileService.uploadFileOSS(file);
        return R.ok().data("url",url);

    }


}
