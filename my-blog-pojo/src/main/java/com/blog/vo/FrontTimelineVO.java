package com.blog.vo;

import com.blog.entity.BlogTimelineArticle;
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
public class FrontTimelineVO implements Serializable {
    private String year;
    private List<BlogTimelineArticle> ArticleList;
}
