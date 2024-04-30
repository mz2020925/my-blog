package com.blog.mapper;

import com.blog.annotation.AutoFillTime;
import com.blog.dto.FrontArticlesByCTIdDTO;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogArticle;
import com.blog.entity.BlogTimelineArticle;
import com.blog.enumeration.SqlOperationType;
import com.blog.vo.ArticleListVO;
import com.blog.vo.FrontArticleDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (BlogArticle)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-28 11:39:04
 */
public interface BlogArticleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FrontArticleDetailVO queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param blogArticle 查询条件
     * @param pageLimit 分页对象
     * @return 对象列表
     */
    List<BlogArticle> queryByConditions(@Param("blogArticle") BlogArticle blogArticle, @Param("pageLimit") PageLimit pageLimit, @Param("sort") Boolean sort);

    /**
     * 统计总行数
     *
     * @param blogArticle 查询条件
     * @return 总行数
     */
    long count(BlogArticle blogArticle);

    /**
     * 新增数据
     *
     * @param blogArticle 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.INSERT)
    int insert(BlogArticle blogArticle);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticle> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogArticle> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticle> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogArticle> entities);

    /**
     * 修改数据
     *
     * @param blogArticle 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.UPDATE)
    int update(BlogArticle blogArticle);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<ArticleListVO> blogHomeGetArticleList(Integer current, Integer size);

    List<BlogArticle> queryArticleListByTagId(FrontArticlesByCTIdDTO frontArticlesByCTIdDTO);

    List<BlogTimelineArticle> blogTimelineGetArticleList(@Param("current")Integer current, @Param("size")Integer size);

    int draftByIds(List<Integer> articleIdList);

    int recoverByIds(List<Integer> articleIdList);

    int deleteByIds(List<Integer> articleIdList);

    List<BlogArticle> queryArticlesByCategoryIds(List<Integer> categoryIdList);
}

