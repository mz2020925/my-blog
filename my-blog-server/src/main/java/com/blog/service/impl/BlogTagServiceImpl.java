package com.blog.service.impl;

import com.blog.dto.*;
import com.blog.entity.BlogTag;
import com.blog.mapper.BlogArticleTagMapper;
import com.blog.mapper.BlogTagMapper;
import com.blog.result.PageResult;
import com.blog.service.BlogTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BlogTag)表服务实现类
 *
 * @author makejava
 * @since 2023-12-28 17:42:22
 */
@Service("blogTagService")
public class BlogTagServiceImpl implements BlogTagService {
    @Resource
    private BlogTagMapper blogTagMapper;
    @Resource
    private BlogArticleTagMapper blogArticleTagMapper;


    public Long count() {
        return blogTagMapper.count(new BlogTag());
    }

    public List getAllTag() {
        List blogTags = blogTagMapper.queryAll();
        return blogTags;
    }

    public PageResult getByPage(BackTagPageDTO backTagPageDTO) {
        Integer current = backTagPageDTO.getCurrent();
        Integer size = backTagPageDTO.getSize();
        PageLimit pageLimit = new PageLimit(current, size);

        BlogTag blogTag = new BlogTag();
        String tagName = backTagPageDTO.getTagName();
        blogTag.setTagName(tagName);

        return queryByPage(blogTag, pageLimit, Boolean.FALSE);
    }

    public void add(BackTagAddEditDTO backTagAddEditDTO) {
        BlogTag blogTag = new BlogTag();
        blogTag.setTagName(backTagAddEditDTO.getTagName());
        blogTagMapper.insert(blogTag);
    }

    public void update(BackTagAddEditDTO backTagAddEditDTO) {
        BlogTag blogTag = new BlogTag();
        blogTag.setId(backTagAddEditDTO.getId());
        blogTag.setTagName(backTagAddEditDTO.getTagName());
        blogTagMapper.update(blogTag);
    }

    public void deleteByIds(BackNeedIdListDTO backNeedIdListDTO) {
        List<Integer> tagIdList = backNeedIdListDTO.getIdList();
        // 删去标签表 中的tag数据
        blogTagMapper.deleteBatch(tagIdList);
        // 删去 文章标签关系表 中的tag数据
        blogArticleTagMapper.deleteByTagIds(tagIdList);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlogTag queryById(Integer id) {
        return this.blogTagMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param blogTag   筛选条件
     * @param pageLimit 分页对象
     * @return 查询结果
     */
    public PageResult queryByPage(BlogTag blogTag, PageLimit pageLimit, Boolean sort) {
        long total = this.blogTagMapper.count(blogTag);
        return new PageResult(this.blogTagMapper.queryAllByLimit(blogTag, pageLimit, sort), total, pageLimit.getCurrent(), pageLimit.getPageSize());
    }

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    public BlogTag insert(BlogTag blogTag) {
        this.blogTagMapper.insert(blogTag);
        return blogTag;
    }

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    public BlogTag update(BlogTag blogTag) {
        this.blogTagMapper.update(blogTag);
        return this.queryById(blogTag.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.blogTagMapper.deleteById(id) > 0;
    }

}
