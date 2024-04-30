package com.blog.vo;

import com.blog.entity.BlogConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrontHomeStatisticVO implements Serializable {
    private Long articleCount;
    private Long categoryCount;
    private Long tagCount;
}
