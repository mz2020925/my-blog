package com.blog.service.impl;

import com.blog.entity.BlogMessage;
import com.blog.mapper.BlogMessageMapper;
import com.blog.service.BlogMessageService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (BlogMessage)表服务实现类
 *
 * @author makejava
 * @since 2024-01-03 15:05:42
 */
@Service("blogMessageService")
public class BlogMessageServiceImpl implements BlogMessageService {
    @Resource
    private BlogMessageMapper blogMessageMapper;


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BlogMessage queryById(Integer id) {
        return this.blogMessageMapper.queryById(id);
    }

    /**
     * 代码生成-分页查询
     *
     * @param blogMessage 筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<BlogMessage> queryByPage(BlogMessage blogMessage, PageRequest pageRequest) {
        long total = this.blogMessageMapper.count(blogMessage);
        return new PageImpl<>(this.blogMessageMapper.queryAllByLimit(blogMessage, pageRequest), pageRequest, total);
    }

    /**
     * 代码生成-新增数据
     *
     * @param blogMessage 实例对象
     * @return 实例对象
     */
    @Override
    public BlogMessage insert(BlogMessage blogMessage) {
        this.blogMessageMapper.insert(blogMessage);
        return blogMessage;
    }

    /**
     * 代码生成-修改数据
     *
     * @param blogMessage 实例对象
     * @return 实例对象
     */
    @Override
    public BlogMessage update(BlogMessage blogMessage) {
        this.blogMessageMapper.update(blogMessage);
        return this.queryById(blogMessage.getId());
    }

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.blogMessageMapper.deleteById(id) > 0;
    }
}
