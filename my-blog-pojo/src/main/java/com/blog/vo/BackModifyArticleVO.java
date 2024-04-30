package com.blog.vo;


import com.blog.entity.BlogCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackModifyArticleVO implements Serializable {
    
    private Integer id;
    private String articleTitle;
    private BlogCategory category;  // 这个不用补充
    private Integer categoryId;
    private List tagIdList;  // 待补充
    private List tagList;  // 这个不用补充
    private Integer authorId;
    private String articleContent;
    private String articleCover;
    private Integer isTop; // 置顶 1 置顶 2 取消置顶
    private Integer order; // 置顶文章的排序
    private Integer status; // 状态 1 公开 2 私密 3 回收站（相当于草稿）
    private Integer type; // 类型 1 原创 2 翻译 3 转载
    private String originUrl; // 原文链接 翻译或转载才需要填
    private List coverList;  // 这个不用补充
    private String articleDescription; // 文章描述
}
