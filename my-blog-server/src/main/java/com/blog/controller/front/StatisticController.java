package com.blog.controller.front;

import com.blog.result.Result;
import com.blog.service.*;
import com.blog.vo.FrontHomeStatisticVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 数据统计控制层
 */
@RestController
@RequestMapping("/statistic")
@Slf4j
@Api(tags = "数据统计")
public class StatisticController {
    /**
     * 服务对象
     */
    @Resource
    private BlogStatisticService blogStatisticService;

    /**
     * 获取数据统计
     * @return
     */
    @GetMapping
    @ApiOperation("获取数据统计")
    public Result homeGetStatistic() {
        log.info("获取数据统计");
        FrontHomeStatisticVO frontHomeStatisticVO = blogStatisticService.homeGetStatistic();
        return Result.success(frontHomeStatisticVO);
    }
}
