package com.blog.handler;


import com.blog.exception.BaseException;
import com.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice  // 为什么全局异常处理器没有添加@Component注解？因为RestControllerAdvice继承了很多注解（包含@Component），相当于很多注解的合并
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获异常信息
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("全局异常处理器-业务异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获数据库异常
     */
    // @ExceptionHandler
    // public Result sqlExceptionHandler(SQLException ex){
    //     log.error("全局异常处理器-数据库异常信息：{}", ex.getMessage());
    //     return Result.error(ex.getMessage());
    // }
}
