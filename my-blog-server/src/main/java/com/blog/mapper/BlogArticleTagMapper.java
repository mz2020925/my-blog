package com.blog.mapper;

import com.blog.annotation.AutoFillTime;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogArticleTag;
import com.blog.enumeration.SqlOperationType;
import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * (BlogArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-28 17:36:05
 */
public interface BlogArticleTagMapper {
    List<String> getTagNamesByArticleId(Integer articleId);

    List<Integer> getTagIdsByArticleId(Integer id);


    Integer queryAllByTagIds(List<Integer> tagIdList);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogArticleTag queryById(Integer id);

    /**
     * 查询指定行数据
     */
    List<BlogArticleTag> queryAllByLimit(@Param("blogArticleTag") BlogArticleTag blogArticleTag, @Param("pageLimit") PageLimit pageLimit, @Param("sort") Boolean sort);

    /**
     * 统计总行数
     *
     * @param blogArticleTag 查询条件
     * @return 总行数
     */
    long count(BlogArticleTag blogArticleTag);

    /**
     * 新增数据
     *
     * @param blogArticleTag 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.INSERT)
    int insert(BlogArticleTag blogArticleTag);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticleTag> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogArticleTag> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogArticleTag> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogArticleTag> entities);

    /**
     * 修改数据
     *
     * @param blogArticleTag 实例对象
     * @return 影响行数
     */
    @AutoFillTime(SqlOperationType.UPDATE)
    int update(BlogArticleTag blogArticleTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteByArticleId(Integer articleId);

    int deleteByTagIds(List<Integer> tagIdList);
}

