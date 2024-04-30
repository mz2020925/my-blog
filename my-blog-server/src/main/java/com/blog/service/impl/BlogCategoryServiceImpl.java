package com.blog.service.impl;

import com.blog.constant.MessageConstant;
import com.blog.dto.BackCategoryAddEditDTO;
import com.blog.dto.BackCategoryPageDTO;
import com.blog.dto.BackNeedIdListDTO;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogArticle;
import com.blog.entity.BlogCategory;
import com.blog.exception.ArticleExistInTheCategory;
import com.blog.mapper.BlogArticleMapper;
import com.blog.mapper.BlogCategoryMapper;
import com.blog.result.PageResult;
import com.blog.service.BlogCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BlogCategory)表服务实现类
 *
 * @author makejava
 * @since 2023-12-30 22:27:31
 */
@Service("blogCategoryService")
public class BlogCategoryServiceImpl implements BlogCategoryService {
    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    @Resource
    private BlogArticleMapper blogArticleMapper;


    public Long count() {
        return blogCategoryMapper.count(new BlogCategory());
    }


    public List<BlogCategory> getAllCategory() {
        return blogCategoryMapper.getCategoryDictionary();
    }

    public PageResult getByPage(BackCategoryPageDTO backCategoryPageDTO) {
        // 分页条件
        Integer current = backCategoryPageDTO.getCurrent();
        Integer size = backCategoryPageDTO.getSize();
        PageLimit pageLimit = new PageLimit(current, size);
        // 字段条件
        BlogCategory blogCategory = new BlogCategory();
        String CategoryName = backCategoryPageDTO.getCategoryName();
        blogCategory.setCategoryName(CategoryName);
        // 分页查询
        List<BlogCategory> blogCategories = blogCategoryMapper.queryAllByLimit(blogCategory, pageLimit, null);
        Long total = blogCategoryMapper.count(null);
        return new PageResult(blogCategories, total, current, size);
    }

    public void add(BackCategoryAddEditDTO backCategoryAddEditDTO) {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setCategoryName(backCategoryAddEditDTO.getCategoryName());
        blogCategoryMapper.insert(blogCategory);
    }

    @Override
    public void update(BackCategoryAddEditDTO backCategoryAddEditDTO) {
        BlogCategory blogCategory = new BlogCategory();
        blogCategory.setId(backCategoryAddEditDTO.getId());
        blogCategory.setCategoryName(backCategoryAddEditDTO.getCategoryName());
        blogCategoryMapper.update(blogCategory);
    }

    public void deleteByIds(BackNeedIdListDTO categoryIdListDTO) {
        List<Integer> categoryIdList = categoryIdListDTO.getIdList();
        List<BlogArticle> blogArticles = blogArticleMapper.queryArticlesByCategoryIds(categoryIdList);
        if (blogArticles != null && blogArticles.size() != 0) {
            throw new ArticleExistInTheCategory(MessageConstant.ARTICLE_EXIST_IN_THE_CATEGORY);
        }
        blogCategoryMapper.deleteByIds(categoryIdList);
    }


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogCategory queryById(Integer id) {
        return this.blogCategoryMapper.queryById(id);
    }

    /**
     * 代码生成-分页查询
     *
     * @param blogCategory 筛选条件
     * @param pageLimit    分页对象
     * @return 查询结果
     */
    public PageResult queryByPage(BlogCategory blogCategory, PageLimit pageLimit) {
        long total = this.blogCategoryMapper.count(blogCategory);
        return new PageResult(this.blogCategoryMapper.queryAllByLimit(blogCategory, pageLimit, null), total, pageLimit.getCurrent(), pageLimit.getSize());
    }

    /**
     * 代码生成-新增数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    @Override
    public BlogCategory insert(BlogCategory blogCategory) {
        this.blogCategoryMapper.insert(blogCategory);
        return blogCategory;
    }

    /**
     * 代码生成-修改数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    @Override
    public BlogCategory update(BlogCategory blogCategory) {
        this.blogCategoryMapper.update(blogCategory);
        return this.queryById(blogCategory.getId());
    }

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogCategoryMapper.deleteById(id) > 0;
    }

}
