package com.blog.exception;

public class ArticleExistInTheCategory extends BaseException{
    public ArticleExistInTheCategory(){}

    public ArticleExistInTheCategory(String msg){
        super(msg);
    }
}
