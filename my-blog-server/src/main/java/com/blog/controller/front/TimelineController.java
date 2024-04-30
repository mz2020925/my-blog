package com.blog.controller.front;

import com.blog.result.PageResult;
import com.blog.result.Result;
import com.blog.service.BlogArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ()表控制层
 */
@RestController
@RequestMapping("")
@Slf4j
@Api(tags = "时间轴")
public class TimelineController {
    /**
     * 服务对象
     */
    @Resource
    private BlogArticleService blogArticleService;

    /**
     * 时间轴文章列表
     * @return
     */
    @GetMapping("/article/blogTimelineGetArticleList/{current}/{size}")
    @ApiOperation(value = "时间轴文章列表")
    public Result blogTimelineGetArticleList(@PathVariable("current") Integer current, @PathVariable("size") Integer size) {
        log.info("时间轴文章列表");
        PageResult pageResult = blogArticleService.blogTimelineGetArticleList(current, size);
        return Result.success(pageResult);
    }

}

