package com.dilo.eduservice.controller;

import com.dilo.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api(description = "模拟登录")
@RestController
@RequestMapping("/eduuser")
//@CrossOrigin//跨域注解
public class EduLoginController {

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public R login(){
      return R.ok().data("token","admin");
    }


    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","admin").
                data("name","admin").
                data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
