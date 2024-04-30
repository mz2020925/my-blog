package com.blog.service;

import com.blog.dto.*;
import com.blog.entity.BlogArticle;
import com.blog.result.PageResult;
import com.blog.vo.BackArticleDetailVO;
import com.blog.vo.BackModifyArticleVO;
import com.blog.vo.FrontArticleDetailVO;

import java.util.List;

/**
 * (BlogArticle)表服务接口
 *
 * @author makejava
 * @since 2023-12-28 11:39:05
 */
public interface BlogArticleService {

    Long count();

    PageResult blogHomeGetArticleList(Integer current, Integer size);

    PageResult getArticleListByCategoryId(FrontArticlesByCTIdDTO frontArticlesByCTIdDTO);

    List<BlogArticle> getArticleListByTagId(FrontArticlesByCTIdDTO frontArticlesByCTIdDTO);

    PageResult blogTimelineGetArticleList(Integer current, Integer size);

    PageResult getByPage(BackArticleListDTO backArticleListDTO);

    BackModifyArticleVO getArticleById(Integer id);

    FrontArticleDetailVO frontGetArticleById(Integer id);

    Boolean titleExist(BackArticleTitleExistDTO backArticleTitleExistDTO);

    void update(BackArticleAddDTO backArticleAddDTO);

    void add(BackArticleAddDTO backArticleAddDTO);

    void deleteArticle(Integer id, Integer status);

    void revertArticle(Integer id);

    void isArticlePublic(Integer id);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogArticle queryById(Integer id);

    /**
     * 分页查询
     *
     * @param blogArticle 筛选条件
     * @param pageLimit   分页对象
     * @return 查询结果
     */
    PageResult queryByPage(BlogArticle blogArticle, PageLimit pageLimit);

    /**
     * 新增数据
     *
     * @param blogArticle 实例对象
     * @return 实例对象
     */
    BlogArticle insert(BlogArticle blogArticle);

    /**
     * 修改数据
     *
     * @param blogArticle 实例对象
     * @return 实例对象
     */
    void update(BlogArticle blogArticle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */

    // 下面是管理前端的方法
    boolean deleteById(Integer id);

    void draftByIds(BackNeedIdListDTO articleIdListDTO);

    void publishByIds(BackNeedIdListDTO articleIdListDTO);

    void deleteByIds(BackNeedIdListDTO articleIdListDTO);

    BackArticleDetailVO getDetailById(Integer id);


}
