package com.blog.service.impl;

import com.blog.constant.ArticleStatusConstant;
import com.blog.constant.MessageConstant;
import com.blog.context.BaseContext;
import com.blog.dto.*;
import com.blog.entity.*;
import com.blog.exception.ArticleTitleExistException;
import com.blog.mapper.*;
import com.blog.result.PageResult;
import com.blog.service.BlogArticleService;
import com.blog.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * (BlogArticle)表服务实现类
 *
 * @author makejava
 * @since 2023-12-28 11:39:05
 */
@Service("blogArticleService")
@Slf4j
public class BlogArticleServiceImpl implements BlogArticleService {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Resource
    private BlogArticleTagMapper blogArticleTagMapper;


    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    @Resource
    private BlogAdminMapper blogAdminMapper;

    /**
     * 展示端根据文章id获取文章详情
     */
    public FrontArticleDetailVO frontGetArticleById(Integer id) {
        FrontArticleDetailVO frontArticleDetailVO = blogArticleMapper.queryById(id);
        List<String> tagNames = blogArticleTagMapper.getTagNamesByArticleId(frontArticleDetailVO.getId());
        frontArticleDetailVO.setTagNameList(tagNames);
        BlogAdmin blogAdmin = blogAdminMapper.queryById(frontArticleDetailVO.getAuthorId());
        frontArticleDetailVO.setAuthorName(blogAdmin.getNickName());

        return frontArticleDetailVO;
    }


    /**
     * 展示端主页中分页获取文章
     */
    public PageResult blogHomeGetArticleList(Integer current, Integer size) {
        // 双表查询先添加上categoryName
        List<ArticleListVO> results = blogArticleMapper.blogHomeGetArticleList((current - 1) * size, size);
        // 遍历所有文章
        for (ArticleListVO result : results) {
            // 根据每篇文章的id查询对应的所有标签名称
            List<String> tagNames = blogArticleTagMapper.getTagNamesByArticleId(result.getId());
            // 赋值给标签列表
            result.setTagNameList(tagNames);
        }
        Long totalCount = blogArticleMapper.count(new BlogArticle());
        return new PageResult(results, totalCount, current, size);
    }

    /**
     * 展示端根据分类获取文章
     */
    public PageResult getArticleListByCategoryId(FrontArticlesByCTIdDTO frontArticlesByCTIdDTO) {
        // 创建查询条件
        // 字段条件
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setCategoryId(frontArticlesByCTIdDTO.getId());
        // 分页条件
        Integer current = frontArticlesByCTIdDTO.getCurrent();
        Integer size = frontArticlesByCTIdDTO.getSize();
        PageLimit pageLimit = new PageLimit(current, size);
        List<BlogArticle> result = blogArticleMapper.queryByConditions(blogArticle, pageLimit, null);
        // 该分类下文章数
        Long countByCategory = blogArticleMapper.count(blogArticle);
        return new PageResult(result, countByCategory, current, size);
    }

    /**
     * 展示端根据标签获取文章
     */
    public List<BlogArticle> getArticleListByTagId(FrontArticlesByCTIdDTO frontArticlesByCTIdDTO) {
        return blogArticleMapper.queryArticleListByTagId(frontArticlesByCTIdDTO);
    }

    /**
     * 展示端时间轴
     */
    public PageResult blogTimelineGetArticleList(Integer current, Integer size) {
        // 求出包含年份的时间轴文章摘要列表
        List<BlogTimelineArticle> blogTimelineArticles = blogArticleMapper.blogTimelineGetArticleList((current - 1) * size, size);
        System.out.println(blogTimelineArticles);
        /* 这里求时间轴的业务逻辑写法想了挺久 */
        // 利用map进行去重（同年份的放一起），同时按照年份降序排列
        Map<String, FrontTimelineVO> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (BlogTimelineArticle item : blogTimelineArticles) {
            // 求出年份，作为key
            String year = item.getCreatedYear();
            // map[ {year, FrontTimelineVO{year, articleList(BlogTimelineArticle)}}, ]
            if (!map.containsKey(year)) {
                FrontTimelineVO frontTimelineVO = new FrontTimelineVO(year, new ArrayList<BlogTimelineArticle>() {{
                    add(item);
                }});
                map.put(year, frontTimelineVO);
            } else {
                map.get(year).getArticleList().add(item);
            }
        }
        // System.out.println(map.values());

        List<FrontTimelineVO> result = new ArrayList<>(map.values());
        // System.out.println(result);

        // 文章总数
        Long count = blogArticleMapper.count(null);

        return new PageResult(result, count, current, size);
    }


    /**
     * 管理端文章列表
     */
    public PageResult getByPage(BackArticleListDTO backArticleListDTO) {
        // 分页条件
        Integer current = backArticleListDTO.getCurrent();
        Integer size = backArticleListDTO.getSize();
        PageLimit pageLimit = new PageLimit(current, size);
        // 字段条件
        BlogArticle blogArticle = new BlogArticle();
        BeanUtils.copyProperties(backArticleListDTO, blogArticle);
        // 根据上面两个条件查询
        List<BlogArticle> results = blogArticleMapper.queryByConditions(blogArticle, pageLimit, null);
        // 遍历所有文章，并封装成返回响应
        List<ArticleListVO> articleListVOS = new ArrayList<>();
        for (BlogArticle result : results) {
            ArticleListVO articleListVO = new ArticleListVO();
            BeanUtils.copyProperties(result, articleListVO);
            // 赋值给分类名称
            BlogCategory blogCategory = blogCategoryMapper.queryById(articleListVO.getCategoryId());
            if (blogCategory != null) {
                String categoryName = blogCategory.getCategoryName();
                articleListVO.setCategoryName(categoryName);
            }
            // 根据每篇文章的id查询对应的所有标签名称
            List<String> tagNames = blogArticleTagMapper.getTagNamesByArticleId(articleListVO.getId());
            // 赋值给标签列表
            articleListVO.setTagNameList(tagNames);
            // 查询作者名称并赋值
            BlogAdmin blogAdmin = blogAdminMapper.queryById(articleListVO.getAuthorId());
            articleListVO.setAuthorName(blogAdmin.getNickName());

            // 放入文章列表
            articleListVOS.add(articleListVO);
        }
        Long total = blogArticleMapper.count(new BlogArticle());
        return new PageResult(articleListVOS, total, current, size);
    }

    /**
     * 管理端修改文章
     */
    public void update(BackArticleAddDTO backArticleAddDTO) {
        // 删掉 文章标签关系表 原有的数据
        blogArticleTagMapper.deleteByArticleId(backArticleAddDTO.getArticleId());
        // 增添 文章标签关系表 新数据
        List<BlogArticleTag> blogArticleTags = commonUpdateAndAdd1(backArticleAddDTO);
        if (blogArticleTags != null && blogArticleTags.size() != 0) {
            blogArticleTagMapper.insertBatch(blogArticleTags);
        }
        // 修改 文章表 数据
        blogArticleMapper.update(commonUpdateAndAdd2(backArticleAddDTO));
    }

    /**
     * 管理端新增文章
     */
    public void add(BackArticleAddDTO backArticleAddDTO) {
        // 新增文章表 数据
        BlogArticle blogArticle = commonUpdateAndAdd2(backArticleAddDTO);
        blogArticleMapper.insert(blogArticle);
        // 新增 文章标签关系表 数据
        backArticleAddDTO.setArticleId(blogArticle.getId());
        List<BlogArticleTag> blogArticleTags = commonUpdateAndAdd1(backArticleAddDTO);
        if (blogArticleTags != null && blogArticleTags.size() != 0) {
            blogArticleTagMapper.insertBatch(blogArticleTags);
        }
    }


    /**
     * update()和add()方法共同的代码部分
     * 插入 文章标签表 若干行数据
     */
    public List<BlogArticleTag> commonUpdateAndAdd1(BackArticleAddDTO backArticleAddDTO) {
        ArrayList<BlogArticleTag> blogArticleTagList = new ArrayList<>();
        List<Integer> tagIdList = backArticleAddDTO.getTagIdList();
        for (Integer tagId : tagIdList) {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            // 构造文章标签表一行数据
            blogArticleTag.setArticleId(backArticleAddDTO.getArticleId());
            blogArticleTag.setTagId(tagId);
            blogArticleTag.setCreatedAt(LocalDateTime.now());
            blogArticleTag.setUpdatedAt(LocalDateTime.now());

            blogArticleTagList.add(blogArticleTag);
        }
        return blogArticleTagList;
    }


    /**
     * update()和add()方法共同的代码部分
     * 插入 文章表 一行数据
     */
    public BlogArticle commonUpdateAndAdd2(BackArticleAddDTO backArticleAddDTO) {
        Integer articleId = backArticleAddDTO.getArticleId();

        BlogArticle blogArticle = new BlogArticle();
        // 根据id是否为null，判断是新增操作还是修改操作
        if (articleId == null) {
            // 新增文章时，检查给定的标题是否和数据库中的文章标题重复
            String articleTitle = backArticleAddDTO.getArticleTitle();
            blogArticle.setArticleTitle(articleTitle);
            List<BlogArticle> blogArticleList = blogArticleMapper.queryByConditions(blogArticle, null, null);
            if (blogArticleList != null && blogArticleList.size() != 0) {
                throw new ArticleTitleExistException(MessageConstant.ARTICLE_TITLE_EXIST);
            }

        } else {
            blogArticle.setId(backArticleAddDTO.getArticleId());
        }
        blogArticle.setArticleTitle(backArticleAddDTO.getArticleTitle());
        blogArticle.setArticleContent(backArticleAddDTO.getArticleContent());
        blogArticle.setArticleCover(backArticleAddDTO.getArticleCover());
        blogArticle.setCategoryId(backArticleAddDTO.getCategoryId());
        blogArticle.setStatus(backArticleAddDTO.getStatus());
        blogArticle.setType(backArticleAddDTO.getType());
        // TODO TreadLocal中的BaseContext这个用法必须是同一个线程，现在我用的不是一个线程。所以这个BaseContext用不了，可是为什么苍穹外卖能用
        // Integer currentId = BaseContext.getCurrentId();
        blogArticle.setAuthorId(1);
        // log.info("adminId: {}", currentId);  // 打印当前登录的管理员的id

        return blogArticle;
    }

    /**
     * 修改文章状态为 2
     *
     * @param articleIdListDTO
     */
    public void draftByIds(BackNeedIdListDTO articleIdListDTO) {
        List<Integer> articleIdList = articleIdListDTO.getIdList();
        blogArticleMapper.draftByIds(articleIdList);
    }

    /**
     * 修改文章状态为 1
     *
     * @param articleIdListDTO
     */
    public void publishByIds(BackNeedIdListDTO articleIdListDTO) {
        List<Integer> articleIdList = articleIdListDTO.getIdList();
        blogArticleMapper.recoverByIds(articleIdList);
    }

    /**
     * 删除文章
     *
     * @param articleIdListDTO
     */
    public void deleteByIds(BackNeedIdListDTO articleIdListDTO) {
        List<Integer> articleIdList = articleIdListDTO.getIdList();
        blogArticleMapper.deleteByIds(articleIdList);
    }

    /**
     * 获取文章详情
     *
     * @param id
     */
    public BackArticleDetailVO getDetailById(Integer id) {
        BackArticleDetailVO backArticleDetailVO = new BackArticleDetailVO();

        BlogArticle blogArticle = blogArticleMapper.queryById(id);
        BeanUtils.copyProperties(blogArticle, backArticleDetailVO);  // 这里已经完成了文章的type和articleCover的赋值

        // 根据每篇文章的id查询对应的所有标签id
        List<Integer> tagIds = blogArticleTagMapper.getTagIdsByArticleId(id);
        // 赋值给标签列表
        backArticleDetailVO.setTagIdList(tagIds);
        // 给articleId赋值
        backArticleDetailVO.setArticleId(id);

        return backArticleDetailVO;
    }


    /********************** blog-admin-v3里面需要的后端接口 *************************/
    public BackModifyArticleVO getArticleById(Integer id) {
        BlogArticle blogArticle = blogArticleMapper.queryById(id);
        BackModifyArticleVO backModifyArticleVO = new BackModifyArticleVO();
        BeanUtils.copyProperties(blogArticle, backModifyArticleVO);
        // 设置标签id列表
        List<Integer> tagIds = blogArticleTagMapper.getTagIdsByArticleId(id);
        backModifyArticleVO.setTagIdList(tagIds);
        return backModifyArticleVO;
    }

    public Boolean titleExist(BackArticleTitleExistDTO backArticleTitleExistDTO) {
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setArticleTitle(backArticleTitleExistDTO.getArticleTitle());

        List<BlogArticle> blogArticles = blogArticleMapper.queryByConditions(blogArticle, null, null);
        if (blogArticles != null && blogArticles.size() != 0) {
            BlogArticle article = blogArticles.get(0);
            return article.getId().equals(backArticleTitleExistDTO.getId());  // 数据库中存在这个标题，如果id不是传进来的id--说明标题重复
        }
        return false;  // 数据库中不存在这个标题
    }

    public void deleteArticle(Integer id, Integer status) {
        if (status == 1 || status == 2) {
            BlogArticle blogArticle = new BlogArticle();
            blogArticle.setId(id);
            Integer newStatus = status + ArticleStatusConstant.RECYCLE_BIN_DELTA;
            blogArticle.setStatus(newStatus);  // 状态是1、2，假删除（放入回收站）：status = status + ArticleStatusConstant.RECYCLE_BIN_DELTA

            blogArticleMapper.update(blogArticle);
        } else if (status == 3) {
            blogArticleMapper.deleteById(id);  // 状态是3，真删除
        }
    }

    public void revertArticle(Integer id) {
        BlogArticle blogArticle = blogArticleMapper.queryById(id);
        Integer originStatus = blogArticle.getStatus() - ArticleStatusConstant.RECYCLE_BIN_DELTA;
        blogArticle.setStatus(originStatus);  // 从回收站中恢复

        blogArticleMapper.update(blogArticle);
    }

    public void isArticlePublic(Integer id) {
        BlogArticle blogArticle = blogArticleMapper.queryById(id);
        Integer newStatus = blogArticle.getStatus() == 1 ? 2 : 1;
        blogArticle.setStatus(newStatus);
        blogArticleMapper.update(blogArticle);
    }

    /********************** 自动生成代码 *************************/
    /**
     * 获取所有记录数
     */
    public Long count() {
        return blogArticleMapper.count(new BlogArticle());
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlogArticle queryById(Integer id) {
        return this.blogArticleMapper.queryById(id);
    }

    /**
     * 分页查询
     */
    public PageResult queryByPage(BlogArticle blogArticle, PageLimit pageLimit) {
        long total = this.blogArticleMapper.count(blogArticle);
        return new PageResult(this.blogArticleMapper.queryByConditions(blogArticle, pageLimit, null), total, pageLimit.getCurrent(), pageLimit.getSize());
    }

    /**
     * 新增数据
     *
     * @param blogArticle 实例对象
     * @return 实例对象
     */
    public BlogArticle insert(BlogArticle blogArticle) {
        this.blogArticleMapper.insert(blogArticle);
        return blogArticle;
    }

    /**
     * 修改数据
     *
     * @param blogArticle 实例对象
     */
    public void update(BlogArticle blogArticle) {
        this.blogArticleMapper.update(blogArticle);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.blogArticleMapper.deleteById(id) > 0;
    }
}
