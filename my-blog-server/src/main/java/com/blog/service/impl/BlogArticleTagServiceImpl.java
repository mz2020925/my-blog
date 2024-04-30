package com.blog.service.impl;

import com.blog.dto.PageLimit;
import com.blog.entity.BlogArticleTag;
import com.blog.mapper.BlogArticleTagMapper;
import com.blog.result.PageResult;
import com.blog.service.BlogArticleTagService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (BlogArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-12-28 17:36:05
 */
@Service("blogArticleTagService")
public class BlogArticleTagServiceImpl implements BlogArticleTagService {
    @Resource
    private BlogArticleTagMapper blogArticleTagMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogArticleTag queryById(Integer id) {
        return this.blogArticleTagMapper.queryById(id);
    }

    /**
     * 分页查询
     */
    @Override
    public PageResult queryByPage(BlogArticleTag blogArticleTag, PageLimit pageLimit) {
        long total = this.blogArticleTagMapper.count(blogArticleTag);
        return new PageResult(this.blogArticleTagMapper.queryAllByLimit(blogArticleTag, pageLimit, Boolean.FALSE), total, pageLimit.getCurrent(), pageLimit.getSize());
    }

    /**
     * 新增数据
     *
     * @param blogArticleTag 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticleTag insert(BlogArticleTag blogArticleTag) {
        this.blogArticleTagMapper.insert(blogArticleTag);
        return blogArticleTag;
    }

    /**
     * 修改数据
     *
     * @param blogArticleTag 实例对象
     * @return 实例对象
     */
    @Override
    public BlogArticleTag update(BlogArticleTag blogArticleTag) {
        this.blogArticleTagMapper.update(blogArticleTag);
        return this.queryById(blogArticleTag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogArticleTagMapper.deleteById(id) > 0;
    }
}
