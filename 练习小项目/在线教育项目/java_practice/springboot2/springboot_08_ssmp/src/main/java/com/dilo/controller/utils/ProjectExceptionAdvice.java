package com.dilo.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    //拦截所有的异常信息
    @ExceptionHandler
    public Result doException(Exception ex){
        ex.printStackTrace();

        return new Result("服务器故障,清稍后再试!");

    }
}
