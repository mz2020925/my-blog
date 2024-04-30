package com.blog.exception;

public class UsernameNotFoundException extends BaseException{
    public UsernameNotFoundException(){}

    public UsernameNotFoundException(String msg){
        super(msg);
    }
}
