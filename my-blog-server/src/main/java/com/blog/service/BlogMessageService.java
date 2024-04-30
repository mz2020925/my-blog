package com.blog.service;

import com.blog.entity.BlogMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (BlogMessage)表服务接口
 *
 * @author makejava
 * @since 2024-01-03 15:05:42
 */
public interface BlogMessageService {


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogMessage queryById(Integer id);

    /**
     * 代码生成-分页查询
     *
     * @param blogMessage 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<BlogMessage> queryByPage(BlogMessage blogMessage, PageRequest pageRequest);

    /**
     * 代码生成-新增数据
     *
     * @param blogMessage 实例对象
     * @return 实例对象
     */
    BlogMessage insert(BlogMessage blogMessage);

    /**
     * 代码生成-修改数据
     *
     * @param blogMessage 实例对象
     * @return 实例对象
     */
    BlogMessage update(BlogMessage blogMessage);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
