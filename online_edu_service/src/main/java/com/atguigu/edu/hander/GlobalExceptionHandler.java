package com.atguigu.edu.hander;

import com.atguigu.exception.EduException;
import com.atguigu.response.RetVal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//出现异常后在此地处理
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetVal error(Exception e){
        e.printStackTrace();
        System.out.println("出现全局异常了");
       return RetVal.error().message("出现全局异常了");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public RetVal error(ArithmeticException e){
        e.printStackTrace();
        System.out.println("出现局部异常了");
        return RetVal.error().message("出现局部异常了");
    }

    @ExceptionHandler(EduException.class)
    @ResponseBody
    public RetVal error(EduException e){

        return RetVal.error().message(e.getMessage());
    }

}
