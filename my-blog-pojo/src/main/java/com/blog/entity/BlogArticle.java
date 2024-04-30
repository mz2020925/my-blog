package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (BlogArticle)实体类
 *
 * @author makejava
 * @since 2023-12-28 11:39:38
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticle implements Serializable {
    private static final long serialVersionUID = 808924010311787371L;
    
    private Integer id;
    /**
     * 文章标题 不能为空
     */
    private String articleTitle;
    /**
     * 文章作者 不能为空
     */
    private Integer authorId;
    /**
     * 分类id 不能为空
     */
    private Integer categoryId;
    /**
     * 文章内容
     */
    private String articleContent;
    /**
     * 文章缩略图
     */
    private String articleCover;
    /**
     * 是否置顶 1 置顶 2 取消置顶
     */
    private Integer isTop;
    /**
     * 文章状态  1 公开 2 私密 3 草稿箱
     */
    private Integer status;
    /**
     * 文章类型 1 原创 2 转载 3 翻译
     */
    private Integer type;
    /**
     * 原文链接 是转载或翻译的情况下提供
     */
    private String originUrl;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    /**
     * 文章访问次数
     */
    private Integer viewTimes;
    /**
     * 描述信息 不能为空
     */
    private String articleDescription;
    /**
     * 文章点赞次数
     */
    private Integer thumbsUpTimes;
    /**
     * 文章阅读时长
     */
    private Double readingDuration;
    /**
     * 排序 1 最大 往后越小 用于置顶文章的排序
     */
    private Integer order;

}

