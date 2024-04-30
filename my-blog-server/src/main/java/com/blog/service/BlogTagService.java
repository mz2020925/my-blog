package com.blog.service;

import com.blog.dto.*;
import com.blog.entity.BlogTag;
import com.blog.result.PageResult;

import java.util.List;

/**
 * (BlogTag)表服务接口
 *
 * @author makejava
 * @since 2023-12-28 17:42:22
 */
public interface BlogTagService {

    Long count();

    List getAllTag();


    PageResult getByPage(BackTagPageDTO backTagPageDTO);

    void add(BackTagAddEditDTO backTagAddEditDTO);

    void update(BackTagAddEditDTO backTagAddEditDTO);


    void deleteByIds(BackNeedIdListDTO backNeedIdListDTO);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogTag queryById(Integer id);

    /**
     * 分页查询
     *
     * @param blogTag   筛选条件
     * @param pageLimit 分页对象
     * @return 查询结果
     */
    PageResult queryByPage(BlogTag blogTag, PageLimit pageLimit, Boolean sort);

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    BlogTag insert(BlogTag blogTag);

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 实例对象
     */
    BlogTag update(BlogTag blogTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
