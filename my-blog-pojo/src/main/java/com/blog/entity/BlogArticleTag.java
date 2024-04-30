package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (BlogArticleTag)实体类
 *
 * @author makejava
 * @since 2023-12-28 11:39:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticleTag implements Serializable {
    private static final long serialVersionUID = -71901698503213076L;
    
    private Integer id;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 标签id
     */
    private Integer tagId;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

}

