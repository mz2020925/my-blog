package com.blog.controller.back;

import com.blog.context.BaseContext;
import com.blog.dto.BackAdminInfoDTO;
import com.blog.dto.BackAdminLoginDTO;
import com.blog.dto.BackAdminRegisterDTO;
import com.blog.result.Result;
import com.blog.service.BlogAdminService;
import com.blog.vo.BackAdminInfoVO;
import com.blog.vo.BackAdminLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BlogUser)表控制层
 *
 * @author makejava
 * @since 2024-01-03 16:21:06
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@Api(tags = "管理员模块")
public class AdminController {
    /**
     * 业务逻辑层对象
     */
    @Resource
    private BlogAdminService blogAdminService;

    /**
     * 注册
     */
    @PostMapping("/register")
    @ApiOperation(value = "管理员注册")
    public Result registerUser(@RequestBody BackAdminRegisterDTO backAdminRegisterDTO) {
        log.info("管理端-管理员注册");
        BackAdminLoginVO backAdminLoginVO = blogAdminService.register(backAdminRegisterDTO);
        return Result.success(backAdminLoginVO);
    }

    /**
     * 管理员登录
     *
     * @param backAdminLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public Result getLogin(@RequestBody BackAdminLoginDTO backAdminLoginDTO) {
        log.info("管理端-管理员登录");
        BackAdminLoginVO backAdminLoginVO = blogAdminService.login(backAdminLoginDTO);  // 根据用户名查询查询用户信息，并根据查询到的用户信息生成token，在将token封装到返回响应中
        return Result.success(backAdminLoginVO);
    }

    /**
     * 根据id获取当前登录人的信息
     */
    @GetMapping("/getUserInfoById/{id}")  // TODO 看一下苍穹外卖里面获取用户信息是怎么做的，苍穹外卖里面没有
    @ApiOperation(value = "获取当前管理员的信息")
    public Result getUserInfoById(@PathVariable("id") Integer id) {
        log.info("管理端-获取当前管理员的信息：{}", id);
        BackAdminInfoVO backAdminInfoVO = blogAdminService.getUserInfoById(id);
        return Result.success(backAdminInfoVO);
    }

    /**
     * 根据token获取当前登录人的信息
     */
    @GetMapping("/getUserInfoByToken")
    @ApiOperation(value = "获取当前管理员的信息")
    public Result getUserInfoByToken(@RequestHeader("Authorization") String token) {
        log.info("管理端-获取当前管理员的信息：{}", token);
        // Integer currentId = BaseContext.getCurrentId();
        // TODO TreadLocal中的BaseContext这个用法必须是同一个线程，现在我用的不是一个线程。所以这个BaseContext用不了，可是为什么苍穹外卖能用
        BackAdminInfoVO backAdminInfoVO = blogAdminService.getUserInfoById(1);
        return Result.success(backAdminInfoVO);
    }

    /**
     * 更改管理员信息
     */
    @PostMapping("/updateAdminInfo")
    @ApiOperation("更改管理员信息")
    public Result updateAdminInfo(@RequestBody BackAdminInfoDTO backAdminInfoDTO){
        log.info("管理端 - 更改当前管理员信息：{}", backAdminInfoDTO);
        blogAdminService.updateAdminInfo(backAdminInfoDTO);
        return Result.success();
    }


    /************************* 代码生成 *************************/
    /**
     * 代码生成-分页查询
     *
     * @param blogUser 筛选条件
     * @param pageRequestDTO      分页对象
     * @return 查询结果
     */
    // @GetMapping

    /**
     * 代码生成-通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    // @GetMapping("{id}")

    /**
     * 代码生成-新增数据
     *
     * @param blogUser 实体
     * @return 新增结果
     */
    // @PostMapping

    /**
     * 代码生成-编辑数据
     *
     * @param blogUser 实体
     * @return 编辑结果
     */
    // @PutMapping

    /**
     * 代码生成-删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    // @DeleteMapping

}

