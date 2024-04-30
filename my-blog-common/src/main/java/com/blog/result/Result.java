package com.blog.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {  // 这个<T>表示这里一个泛型类，类中存在泛型
    private Integer code;
    private String message;
    private T data;

    // 响应正常的响应码是0
    Result() {
        this.code = 0;
    }

    Result(T t) {
        this.code = 0;
        this.data = t;
    }


    // 只响应code（0）
    public static <T> Result<T> success() {  // 第一个<T>表示这是一个泛型方法，方法中存在泛型；第二个<T>是这个方法的返回值
        return new Result<>();
    }

    // 响应code和data
    public static <T> Result<T> success(T t) {
        return new Result<>(t);
    }

    // 响应code、message
    public static <T> Result<T> message(String message) {
        Result<T> result = new Result<>();
        result.code = 0;
        result.message = message;
        return result;
    }

    // 当出现错误的时候，响应异常code（110）和错误信息
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = 110;
        result.message = msg;
        return result;
    }
}
