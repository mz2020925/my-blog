package com.blog.service;

import com.blog.dto.PageLimit;
import com.blog.entity.BlogConfig;
import com.blog.result.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (BlogConfig)表服务接口
 *
 * @author makejava
 * @since 2023-12-30 19:01:08
 */
public interface BlogConfigService {


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogConfig queryById(Integer id);

    /**
     * 代码生成-分页查询
     *
     * @param blogConfig  筛选条件
     * @param pageLimit 分页对象
     * @return 查询结果
     */
    PageResult queryByPage(BlogConfig blogConfig, PageLimit pageLimit);

    /**
     * 代码生成-新增数据
     *
     * @param blogConfig 实例对象
     * @return 实例对象
     */
    BlogConfig insert(BlogConfig blogConfig);

    /**
     * 代码生成-修改数据
     *
     * @param blogConfig 实例对象
     * @return 实例对象
     */
    BlogConfig update(BlogConfig blogConfig);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    void addView();

    BlogConfig homeGetConfig();
}
