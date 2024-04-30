package com.blog.mapper;

import com.blog.annotation.AutoFillTime;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogCategory;
import com.blog.enumeration.SqlOperationType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (BlogCategory)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-30 22:27:30
 */
public interface BlogCategoryMapper {


    @Select("select * from blog_category")
    List<BlogCategory> getCategoryDictionary();

    int deleteByIds(List<Integer> categoryIdList);


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogCategory queryById(Integer id);

    /**
     * 代码生成-查询指定行数据
     *
     * @param blogCategory 查询条件
     * @param pageLimit     分页对象
     * @return 对象列表
     */
    List<BlogCategory> queryAllByLimit(@Param("blogCategory") BlogCategory blogCategory, @Param("pageLimit") PageLimit pageLimit, @Param("sort") Boolean sort);

    /**
     * 代码生成-统计总行数
     *
     * @param blogCategory 查询条件
     * @return 总行数
     */
    long count(BlogCategory blogCategory);

    /**
     * 代码生成-新增数据
     *
     * @param blogCategory 实例对象
     * @return 影响行数
     */
    @AutoFillTime(SqlOperationType.INSERT)
    int insert(BlogCategory blogCategory);

    /**
     * 代码生成-批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogCategory> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogCategory> entities);

    /**
     * 代码生成-批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogCategory> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogCategory> entities);

    /**
     * 代码生成-修改数据
     *
     * @param blogCategory 实例对象
     * @return 影响行数
     */
    @AutoFillTime(SqlOperationType.UPDATE)
    int update(BlogCategory blogCategory);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


}

