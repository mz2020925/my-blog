package com.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackArticleListDTO implements Serializable {
    private Integer current;
    private Integer size;
    private String articleTitle;
    private Integer tagId;
    private Integer categoryId;
    private Integer isTop;
    private Integer status;
    private LocalDateTime createTime;
}
