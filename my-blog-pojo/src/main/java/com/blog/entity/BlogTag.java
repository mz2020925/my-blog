package com.blog.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BlogTag)实体类
 *
 * @author makejava
 * @since 2023-12-28 11:47:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTag implements Serializable {
    private static final long serialVersionUID = -36966467089348561L;

    private Integer id;
    /**
     * 标签名称 唯一
     */
    private String tagName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

