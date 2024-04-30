package com.blog.vo;

import com.blog.entity.BlogArticle;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontArticleDetailVO extends BlogArticle implements Serializable  {
    // 作者名称
    private String authorName;
    // 分类名称
    private String categoryName;
    // 标签列表
    private List<String> tagNameList = new ArrayList<>();

}
