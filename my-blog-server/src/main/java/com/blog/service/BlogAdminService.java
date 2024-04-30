package com.blog.service;

import com.blog.dto.BackAdminInfoDTO;
import com.blog.dto.BackAdminLoginDTO;
import com.blog.dto.BackAdminRegisterDTO;
import com.blog.entity.BlogAdmin;
import com.blog.vo.BackAdminInfoVO;
import com.blog.vo.BackAdminLoginVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (BlogUser)表服务接口
 *
 * @author makejava
 * @since 2024-01-03 16:21:06
 */
public interface BlogAdminService {

    BackAdminLoginVO login(BackAdminLoginDTO backAdminLoginDTO);

    BackAdminInfoVO getUserInfoById(Integer id);

    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogAdmin queryById(Integer id);

    /**
     * 代码生成-分页查询
     *
     * @param blogAdmin    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<BlogAdmin> queryByPage(BlogAdmin blogAdmin, PageRequest pageRequest);

    /**
     * 代码生成-新增数据
     *
     * @param blogAdmin 实例对象
     * @return 实例对象
     */
    BlogAdmin insert(BlogAdmin blogAdmin);

    /**
     * 代码生成-修改数据
     *
     * @param blogAdmin 实例对象
     * @return 实例对象
     */
    BlogAdmin update(BlogAdmin blogAdmin);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    BackAdminLoginVO register(BackAdminRegisterDTO backAdminRegisterDTO);

    void updateAdminInfo(BackAdminInfoDTO backAdminInfoDTO);
}
