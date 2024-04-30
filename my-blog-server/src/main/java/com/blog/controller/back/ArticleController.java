package com.blog.controller.back;

import com.blog.constant.MessageConstant;
import com.blog.dto.BackArticleAddDTO;
import com.blog.dto.BackArticleListDTO;
import com.blog.dto.BackNeedIdListDTO;
import com.blog.result.PageResult;
import com.blog.result.Result;
import com.blog.service.BlogArticleService;
import com.blog.vo.BackArticleDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ()表控制层
 */
@RestController("backArticleController")
@RequestMapping("/article")
@Slf4j
@Api(tags = "文章管理")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private BlogArticleService blogArticleService;

    /**
     * 条件分页获取文章
     */
    @PostMapping("/getByPage")
    @ApiOperation("条件分页获取文章")
    public Result getByPage(@RequestBody BackArticleListDTO backArticleListDTO) {
        log.info("管理端-条件分页获取文章");
        PageResult pageResult = blogArticleService.getByPage(backArticleListDTO);
        return Result.success(pageResult);
    }

    /**
     * 获取文章详情
     */
    @GetMapping("/getDetailById/{id}")
    @ApiOperation("获取文章详情")
        public Result getDetailById(@PathVariable Integer id) {
        log.info("管理端-获取文章详情");
        BackArticleDetailVO backArticleDetailVO = blogArticleService.getDetailById(id);
        return Result.success(backArticleDetailVO);
    }


    /**
     * 新增文章
     *
     * @param backArticleAddDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增文章")
    public Result add(@RequestBody BackArticleAddDTO backArticleAddDTO) {
        log.info("新增文章");
        blogArticleService.add(backArticleAddDTO);  // TODO 新增这里需要修改两个表！！！
        return Result.success();
    }


    /**
     * 修改文章
     *
     * @param backArticleAddDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改文章")
    public Result update(@RequestBody BackArticleAddDTO backArticleAddDTO) {
        // TODO 修改接口当标签没有选的时候，会报错。
        log.info("修改文章");
        blogArticleService.update(backArticleAddDTO);
        return Result.success();
    }


    @PostMapping("/draftByIds")
    @ApiOperation("文章放入草稿箱")
    public Result draftByIds(@RequestBody BackNeedIdListDTO articleIdListDTO) {
        log.info("文章放入草稿箱");
        blogArticleService.draftByIds(articleIdListDTO);
        return Result.success();
    }


    @PostMapping("/publishByIds")
    @ApiOperation("发布文章")
    public Result publishByIds(@RequestBody BackNeedIdListDTO articleIdListDTO) {
        log.info("发布文章");
        blogArticleService.publishByIds(articleIdListDTO);
        return Result.success();
    }


    @PostMapping("/deleteByIds")
    @ApiOperation("删除文章")
    public Result deleteByIds(@RequestBody BackNeedIdListDTO articleIdListDTO) {
        log.info("删除文章");
        blogArticleService.deleteByIds(articleIdListDTO);
        return Result.success();
    }
}

