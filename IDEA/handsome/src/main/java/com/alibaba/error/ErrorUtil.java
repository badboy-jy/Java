package com.alibaba.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //所有的controller遇到异常后，都会执行该异常处理
public class ErrorUtil {

    @ExceptionHandler(Exception.class)   //指定异常的处理页面
    public String error(){
        return "error";
    }
}
