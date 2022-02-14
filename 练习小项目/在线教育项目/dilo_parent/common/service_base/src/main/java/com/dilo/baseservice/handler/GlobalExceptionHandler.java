package com.dilo.baseservice.handler;

import com.dilo.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception ex){
        ex.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException ex){
        ex.printStackTrace();
        return R.error().message("特定异常处理");
    }

    @ExceptionHandler(DiloException.class)
    @ResponseBody
    public R error(DiloException ex){
        ex.printStackTrace();
        return R.error().code(ex.getCode()).message(ex.getMessage());
    }
}
