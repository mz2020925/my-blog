package com.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BlogCategory)实体类
 *
 * @author makejava
 * @since 2023-12-30 22:27:41
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogCategory implements Serializable {
    private static final long serialVersionUID = 148825640976163877L;
    
    private Integer id;
    /**
     * 分类名称 唯一
     */
    private String categoryName;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

}

