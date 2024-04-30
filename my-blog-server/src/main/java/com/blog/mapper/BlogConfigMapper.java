package com.blog.mapper;

import com.blog.annotation.AutoFillTime;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogConfig;
import com.blog.enumeration.SqlOperationType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (BlogConfig)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-30 19:01:08
 */
public interface BlogConfigMapper {


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogConfig queryById(Integer id);

    /**
     * 代码生成-查询指定行数据
     *
     * @param blogConfig 查询条件
     * @param pageLimit   分页对象
     * @return 对象列表
     */
    List<BlogConfig> queryAllByLimit(@Param("blogConfig") BlogConfig blogConfig, @Param("pageLimit") PageLimit pageLimit, @Param("sort")Boolean sort);

    /**
     * 代码生成-统计总行数
     *
     * @param blogConfig 查询条件
     * @return 总行数
     */
    long count(BlogConfig blogConfig);

    /**
     * 代码生成-新增数据
     *
     * @param blogConfig 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.INSERT)
    int insert(BlogConfig blogConfig);

    /**
     * 代码生成-批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogConfig> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogConfig> entities);

    /**
     * 代码生成-批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogConfig> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogConfig> entities);

    /**
     * 代码生成-修改数据
     *
     * @param blogConfig 实例对象
     * @return 影响行数
     */
    @AutoFillTime(SqlOperationType.UPDATE)
    int update(BlogConfig blogConfig);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

