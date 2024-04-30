package com.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogTimelineArticle {
    private Integer id;
    private String articleTitle;
    private String articleCover;
    private LocalDateTime createdAt;
    private String CreatedYear;
}
