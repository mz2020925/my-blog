package com.blog.service;

import com.blog.dto.BackCategoryAddEditDTO;
import com.blog.dto.BackNeedIdListDTO;
import com.blog.dto.BackCategoryPageDTO;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogCategory;
import com.blog.result.PageResult;

import java.util.List;

/**
 * (BlogCategory)表服务接口
 *
 * @author makejava
 * @since 2023-12-30 22:27:30
 */
public interface BlogCategoryService {

    Long count();

    List<BlogCategory> getAllCategory();

    PageResult getByPage(BackCategoryPageDTO backCategoryPageDTO);

    void add(BackCategoryAddEditDTO backCategoryAddEditDTO);

    void update(BackCategoryAddEditDTO backCategoryAddEditDTO);

    void deleteByIds(BackNeedIdListDTO categoryIdListDTO);

    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogCategory queryById(Integer id);

    /**
     * 代码生成-分页查询
     *
     * @param blogCategory 筛选条件
     * @param pageLimit  分页对象
     * @return 查询结果
     */
    PageResult queryByPage(BlogCategory blogCategory, PageLimit pageLimit);

    /**
     * 代码生成-新增数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    BlogCategory insert(BlogCategory blogCategory);

    /**
     * 代码生成-修改数据
     *
     * @param blogCategory 实例对象
     * @return 实例对象
     */
    BlogCategory update(BlogCategory blogCategory);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
