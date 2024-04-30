package com.blog.service.impl;

import com.blog.dto.PageLimit;
import com.blog.entity.BlogConfig;
import com.blog.mapper.BlogConfigMapper;
import com.blog.result.PageResult;
import com.blog.service.BlogConfigService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (BlogConfig)表服务实现类
 *
 * @author makejava
 * @since 2023-12-30 19:01:08
 */
@Service("blogConfigService")
public class BlogConfigServiceImpl implements BlogConfigService {
    @Resource
    private BlogConfigMapper blogConfigMapper;


    @Override
    public void addView() {
        BlogConfig blogConfig = blogConfigMapper.queryById(1);
        Long viewTime = blogConfig.getViewTime();

        BlogConfig newBlogConfig = new BlogConfig();
        newBlogConfig.setId(1);
        newBlogConfig.setViewTime(viewTime + 1);
        blogConfigMapper.update(newBlogConfig);
    }

    @Override
    public BlogConfig homeGetConfig() {
        return queryById(1);
    }


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogConfig queryById(Integer id) {
        return this.blogConfigMapper.queryById(id);
    }

    /**
     * 代码生成-分页查询
     *
     * @param blogConfig  筛选条件
     * @param pageLimit 分页对象
     * @return 查询结果
     */
    @Override
    public PageResult queryByPage(BlogConfig blogConfig, PageLimit pageLimit) {
        long total = this.blogConfigMapper.count(blogConfig);
        return new PageResult(this.blogConfigMapper.queryAllByLimit(blogConfig, pageLimit, null), total, pageLimit.getCurrent(), pageLimit.getSize());
    }

    /**
     * 代码生成-新增数据
     *
     * @param blogConfig 实例对象
     * @return 实例对象
     */
    @Override
    public BlogConfig insert(BlogConfig blogConfig) {
        this.blogConfigMapper.insert(blogConfig);
        return blogConfig;
    }

    /**
     * 代码生成-修改数据
     *
     * @param blogConfig 实例对象
     * @return 实例对象
     */
    @Override
    public BlogConfig update(BlogConfig blogConfig) {
        this.blogConfigMapper.update(blogConfig);
        return this.queryById(blogConfig.getId());
    }

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogConfigMapper.deleteById(id) > 0;
    }

}
