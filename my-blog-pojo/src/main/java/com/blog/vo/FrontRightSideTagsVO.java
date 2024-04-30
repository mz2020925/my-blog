package com.blog.vo;

import com.blog.entity.BlogTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontRightSideTagsVO extends BlogTag implements Serializable {
    private String color;
}
