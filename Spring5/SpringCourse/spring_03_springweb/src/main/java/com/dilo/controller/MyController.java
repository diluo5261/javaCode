package com.dilo.controller;

import com.dilo.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class MyController {

    @RequestMapping(value = "/mytest",method = RequestMethod.GET)
    public String doSome(){
        return "show";
    }

    @RequestMapping("/register")
    @ResponseBody
    public void register(String name,int age){
        System.out.println(name);
        System.out.println(age);
    }

    @RequestMapping("/register1")
    @ResponseBody
    public void register1(Student student){
        System.out.println(student);

    }

    @RequestMapping("/upload")
    @ResponseBody
    public void upload(String username, MultipartFile upload) throws IOException {
        //获取文件名称
        String fileName = upload.getOriginalFilename();
        System.out.println(fileName);
        //保存文件
        //upload.transferTo(new File("D:\\"+fileName));

    }

    @RequestMapping("/mulupload")
    @ResponseBody
    public void mulupload(String username,MultipartFile[] uploadFiles){
        System.out.println(username);
        for(MultipartFile uploadFile : uploadFiles){
            System.out.println(uploadFile.getOriginalFilename());
        }

    }
}
