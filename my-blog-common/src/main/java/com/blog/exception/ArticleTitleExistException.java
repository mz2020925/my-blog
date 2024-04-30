package com.blog.exception;

public class ArticleTitleExistException extends BaseException{
    public ArticleTitleExistException(){

    }
    public ArticleTitleExistException(String msg){
        super(msg);
    }
}
