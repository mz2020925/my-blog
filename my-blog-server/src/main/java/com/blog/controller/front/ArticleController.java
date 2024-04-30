package com.blog.controller.front;

import com.blog.dto.FrontArticlesByCTIdDTO;
import com.blog.entity.BlogArticle;
import com.blog.result.PageResult;
import com.blog.result.Result;
import com.blog.service.BlogArticleService;
import com.blog.vo.FrontArticleDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BlogArticle)表控制层
 *
 * @author makejava
 * @since 2023-12-28 11:39:04
 */
@RestController("frontArticleController")
@RequestMapping("/article")
@Slf4j
@Api(tags = "主页文章列表-前台")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private BlogArticleService blogArticleService;

    /**
     * 主页中分页获取文章
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/blogHomeGetArticleList/{current}/{size}")
    @ApiOperation(value = "分页获取文章")
    public Result blogHomeGetArticleList(@PathVariable("current") Integer current, @PathVariable("size") Integer size) {
        log.info("分页获取文章：{}，{}", current, size);
        PageResult pageResult = blogArticleService.blogHomeGetArticleList(current, size);
        return Result.success(pageResult);
    }

    @PostMapping("/getArticleListByCategoryId")
    @ApiOperation(value = "根据分类id获取该标签下的文章")
    public Result getArticleListByCategoryId(@RequestBody FrontArticlesByCTIdDTO frontArticlesByCTIdDTO) {
        log.info("根据分类id获取该标签下的文章");
        PageResult pageResult = blogArticleService.getArticleListByCategoryId(frontArticlesByCTIdDTO);
        return Result.success(pageResult);
    }

    @PostMapping("/getArticleListByTagId")
    @ApiOperation(value = "根据标签id获取该标签下的文章")
    public Result getArticleListByTagId(@RequestBody FrontArticlesByCTIdDTO frontArticlesByCTIdDTO) {
        log.info("根据标签id获取该标签下的文章");
        List<BlogArticle> articleListByTagId = blogArticleService.getArticleListByTagId(frontArticlesByCTIdDTO);
        return Result.success(articleListByTagId);
    }

    @GetMapping("/getArticleById/{id}")
    @ApiOperation(value = "根据获取文章详情")
    public Result frontGetArticleById(@PathVariable("id") Integer id) {
        log.info("根据获取文章详情：{}", id);
        FrontArticleDetailVO frontArticleDetailVO = blogArticleService.frontGetArticleById(id);
        return Result.success(frontArticleDetailVO);
    }


}

