package com.blog.mapper;

import com.blog.annotation.AutoFillTime;
import com.blog.entity.BlogMessage;
import com.blog.enumeration.SqlOperationType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * (BlogMessage)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-03 15:05:41
 */
public interface BlogMessageMapper {


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogMessage queryById(Integer id);

    /**
     * 代码生成-查询指定行数据
     *
     * @param blogMessage 查询条件
     * @param pageable    分页对象
     * @return 对象列表
     */
    List<BlogMessage> queryAllByLimit(BlogMessage blogMessage, @Param("pageable") Pageable pageable);

    /**
     * 代码生成-统计总行数
     *
     * @param blogMessage 查询条件
     * @return 总行数
     */
    long count(BlogMessage blogMessage);

    /**
     * 代码生成-新增数据
     *
     * @param blogMessage 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.INSERT)
    int insert(BlogMessage blogMessage);

    /**
     * 代码生成-批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogMessage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogMessage> entities);

    /**
     * 代码生成-批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogMessage> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogMessage> entities);

    /**
     * 代码生成-修改数据
     *
     * @param blogMessage 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.UPDATE)
    int update(BlogMessage blogMessage);

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

