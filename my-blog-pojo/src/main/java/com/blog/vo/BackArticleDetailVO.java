package com.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackArticleDetailVO implements Serializable {
    Integer articleId;
    String articleTitle;
    String articleCover;
    Integer categoryId;
    List tagIdList;
    Integer status;
    Integer type;
    String articleContent;
}
