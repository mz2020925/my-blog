package com.blog.vo;

import com.blog.entity.BlogArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVO extends BlogArticle implements Serializable {
    // 分类名称
    private String categoryName;
    // 标签列表
    private List<String> tagNameList = new ArrayList<>();
    // 作者昵称
    private String authorName;
}
