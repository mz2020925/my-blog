package com.blog.controller.back;

import com.blog.dto.BackNeedIdListDTO;
import com.blog.dto.BackTagAddEditDTO;
import com.blog.dto.BackTagDeleteDTO;
import com.blog.dto.BackTagPageDTO;
import com.blog.result.PageResult;
import com.blog.result.Result;
import com.blog.service.BlogTagService;
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
@RequestMapping("/tag")
@Slf4j
@Api(tags = "标签管理")
public class TagController {
    /**
     * 服务对象
     */
    @Resource
    private BlogTagService blogTagService;

    /**
     * 获取标签列表
     */
    @PostMapping("/getByPage")
    @ApiOperation("获取标签列表")
    public Result getByPage(@RequestBody BackTagPageDTO backTagPageDTO) {
        log.info("获取标签列表");
        PageResult pageResult = blogTagService.getByPage(backTagPageDTO);
        return Result.success(pageResult);
    }

    /**
     * 新增标签
     */
    @PostMapping("/add")
    @ApiOperation("新增标签")
    public Result add(@RequestBody BackTagAddEditDTO backTagAddEditDTO) {
        log.info("新增标签");
        blogTagService.add(backTagAddEditDTO);
        return Result.success();
    }

    /**
     * 修改标签
     */
    @PutMapping("/update")
    @ApiOperation("修改标签")
    public Result update(@RequestBody BackTagAddEditDTO backTagAddEditDTO) {
        log.info("修改标签");
        blogTagService.update(backTagAddEditDTO);
        return Result.success();
    }

    /**
     * 删除标签
     */
    @PostMapping("/deleteByIds")
    @ApiOperation("删除标签")
    public Result deleteByIds(@RequestBody BackNeedIdListDTO backNeedIdListDTO) {
        log.info("删除标签");
        blogTagService.deleteByIds(backNeedIdListDTO);
        return Result.success();
    }

    /**
     * 获取标签字典（获取所有标签）
     * @return
     */
    @GetMapping("/getAllTag")
    @ApiOperation("获取所有标签")
    public Result getAllTag(){
        log.info("获取标签字典（获取所有标签）");
        List rightSideTagsVOs = blogTagService.getAllTag();
        return Result.success(rightSideTagsVOs);
    }
}

