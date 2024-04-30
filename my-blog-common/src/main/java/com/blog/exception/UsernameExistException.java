package com.blog.exception;

public class UsernameExistException extends BaseException{
    public UsernameExistException(){
    }
    public UsernameExistException(String msg){
        super(msg);
    }
}
