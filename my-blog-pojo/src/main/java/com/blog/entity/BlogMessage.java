package com.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BlogMessage)实体类
 *
 * @author makejava
 * @since 2024-01-03 15:06:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogMessage implements Serializable {
    private static final long serialVersionUID = -64659082708827213L;
    
    private Integer id;
    /**
     * 标签
     */
    private String tag;
    /**
     * 留言内容
     */
    private String message;
    /**
     * 字体颜色
     */
    private String color;
    /**
     * 字体大小
     */
    private Integer fontSize;
    /**
     * 背景颜色
     */
    private String bgColor;
    /**
     * 背景图片
     */
    private String bgUrl;
    /**
     * 留言用户的id
     */
    private Integer userId;
    /**
     * 点赞次数
     */
    private Integer likeTimes;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    /**
     * 字体宽度
     */
    private Integer fontWeight;
    /**
     * 游客用户的昵称
     */
    private String nickName;

}

