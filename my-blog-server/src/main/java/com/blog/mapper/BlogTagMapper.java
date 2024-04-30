package com.blog.mapper;

import com.blog.annotation.AutoFillTime;
import com.blog.dto.PageLimit;
import com.blog.entity.BlogTag;
import com.blog.enumeration.SqlOperationType;
import com.blog.vo.FrontRightSideTagsVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (BlogTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-28 17:42:22
 */
public interface BlogTagMapper {

    @Select("select * from blog_tag")
    List<FrontRightSideTagsVO> queryAll();

    int deleteBatch(List<Integer> tagIdList);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BlogTag queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param blogTag  查询条件
     * @param pageLimit 分页对象
     * @return 对象列表
     */
    List<BlogTag> queryAllByLimit(@Param("blogTag") BlogTag blogTag, @Param("pageLimit") PageLimit pageLimit, @Param("sort") Boolean sort);

    /**
     * 统计总行数
     *
     * @param blogTag 查询条件
     * @return 总行数
     */
    long count(BlogTag blogTag);

    /**
     * 新增数据
     *
     * @param blogTag 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.INSERT)
    int insert(BlogTag blogTag);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogTag> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BlogTag> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BlogTag> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BlogTag> entities);

    /**
     * 修改数据
     *
     * @param blogTag 实例对象
     * @return 影响行数
     */
    @AutoFillTime(value = SqlOperationType.UPDATE)
    int update(BlogTag blogTag);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

