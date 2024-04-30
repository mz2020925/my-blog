package com.blog.service.impl;

import com.blog.entity.BlogArticle;
import com.blog.entity.BlogCategory;
import com.blog.entity.BlogTag;
import com.blog.mapper.BlogArticleMapper;
import com.blog.mapper.BlogCategoryMapper;
import com.blog.mapper.BlogTagMapper;
import com.blog.service.BlogStatisticService;
import com.blog.vo.FrontHomeStatisticVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("blogStatisticService")
public class BlogStatisticServiceImpl implements BlogStatisticService {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Override
    public FrontHomeStatisticVO homeGetStatistic() {
        Long articleCount = blogArticleMapper.count(new BlogArticle());
        Long categoryCount = blogCategoryMapper.count(new BlogCategory());
        Long tagCount = blogTagMapper.count(new BlogTag());

        return FrontHomeStatisticVO.builder()
                .articleCount(articleCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .build();
    }
}
