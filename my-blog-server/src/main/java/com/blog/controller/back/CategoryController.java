package com.blog.controller.back;

import com.blog.dto.BackCategoryAddEditDTO;
import com.blog.dto.BackNeedIdListDTO;
import com.blog.dto.BackCategoryPageDTO;
import com.blog.entity.BlogCategory;
import com.blog.result.PageResult;
import com.blog.result.Result;
import com.blog.service.BlogCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * ()表控制层
 */
@RestController
@RequestMapping("/category")
@Slf4j
@Api(tags = "分类管理")
public class CategoryController {
    /**
     * 业务逻辑层对象
     */
    @Resource
    private BlogCategoryService blogCategoryService;

    /**
     * 获取分类列表（分页查询）
     */
    @PostMapping("/getByPage")
    @ApiOperation("获取分类列表")
    public Result getByPage(@RequestBody BackCategoryPageDTO backCategoryPageDTO) {
        log.info("获取分类列表");
        PageResult pageResult = blogCategoryService.getByPage(backCategoryPageDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增分类
     */
    @PostMapping("/add")
    @ApiOperation("新增分类")
    public Result add(@RequestBody BackCategoryAddEditDTO backCategoryAddEditDTO) {
        log.info("新增分类");
        blogCategoryService.add(backCategoryAddEditDTO);
        return Result.success();
    }

    /**
     * 修改分类
     */
    @PutMapping("/update")
    @ApiOperation("修改分类")
    public Result update(@RequestBody BackCategoryAddEditDTO backCategoryAddEditDTO) {
        log.info("修改分类");
        blogCategoryService.update(backCategoryAddEditDTO);
        return Result.success();
    }

    /**
     * 删除分类
     */
    @PostMapping("/deleteByIds")
    @ApiOperation("删除分类")
    public Result deleteByIds(@RequestBody BackNeedIdListDTO categoryIdListDTO) {
        log.info("删除分类");
        blogCategoryService.deleteByIds(categoryIdListDTO);
        return Result.success();
    }

    /**
     * 获取分类字典（获取所有分类）
     *
     * @return
     */
    @GetMapping("/getAllCategory")
    @ApiOperation("获取所有分类")
    public Result getAllCategory() {
        log.info("获取分类字典（获取所有分类）");
        List<BlogCategory> blogCategories = blogCategoryService.getAllCategory();
        return Result.success(blogCategories);
    }
}
