package com.blog.service;

import com.blog.dto.PageLimit;
import com.blog.entity.BlogArticleTag;
import com.blog.result.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (BlogArticleTag)表服务接口
 *
 * @author makejava
 * @since 2023-12-28 17:36:05
 */
public interface BlogArticleTagService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogArticleTag queryById(Integer id);

    /**
     * 分页查询
     */
    PageResult queryByPage(BlogArticleTag blogArticleTag, PageLimit pageLimit);

    /**
     * 新增数据
     *
     * @param blogArticleTag 实例对象
     * @return 实例对象
     */
    BlogArticleTag insert(BlogArticleTag blogArticleTag);

    /**
     * 修改数据
     *
     * @param blogArticleTag 实例对象
     * @return 实例对象
     */
    BlogArticleTag update(BlogArticleTag blogArticleTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
