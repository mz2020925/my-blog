package com.blog.controller.front;

import com.blog.entity.BlogConfig;
import com.blog.result.Result;
import com.blog.service.BlogConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * (BlogConfig)表控制层
 *
 * @author makejava
 * @since 2023-12-30 19:01:08
 */
@RestController
@RequestMapping("/config")
@Slf4j
@Api(tags = "主页侧边栏-前台")
public class ConfigController {
    /**
     * 服务对象
     */
    @Resource
    private BlogConfigService blogConfigService;
    /**
     * 首页获取网站config
     *
     * @return
     */
    @GetMapping
    @ApiOperation("首页获取网站config")
    public Result homeGetConfig() {
        log.info("首页获取网站config");
        BlogConfig blogConfig = blogConfigService.homeGetConfig();
        return Result.success(blogConfig);
    }


    /**
     * 增加网站访问量
     */
    @PutMapping("/addView")
    @ApiOperation("增加网站访问量")
    public Result addView() {
        log.info("增加网站访问量");
        blogConfigService.addView();
        return Result.success();
    }

    /**
     * 获取所有的背景图片
     */
    @GetMapping("/pageHeader/getAll")
    @ApiOperation(value = "获取所有的背景图片")
    public Result getAllPageHeader(){
        log.info("获取所有的背景图片");
        // blogConfigService.getAllPageHeader();
        return Result.success(new ArrayList<String>());
    }
}
