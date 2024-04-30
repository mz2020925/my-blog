package com.blog.service.impl;

import com.blog.constant.JwtClaimsConstant;
import com.blog.constant.MessageConstant;
import com.blog.dto.BackAdminInfoDTO;
import com.blog.dto.BackAdminLoginDTO;
import com.blog.dto.BackAdminRegisterDTO;
import com.blog.entity.BlogAdmin;
import com.blog.exception.PasswordErrorException;
import com.blog.exception.UsernameExistException;
import com.blog.exception.UsernameNotFoundException;
import com.blog.mapper.BlogAdminMapper;
import com.blog.properties.JwtProperties;
import com.blog.service.BlogAdminService;
import com.blog.utils.JwtUtil;
import com.blog.vo.BackAdminInfoVO;
import com.blog.vo.BackAdminLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * (BlogUser)表服务实现类
 *
 * @author makejava
 * @since 2024-01-03 16:21:06
 */
@Service("blogAdminService")
public class BlogAdminServiceImpl implements BlogAdminService {
    @Resource
    private BlogAdminMapper blogAdminMapper;


    @Autowired
    private JwtProperties jwtProperties;

    public BackAdminLoginVO login(BackAdminLoginDTO backAdminLoginDTO) {

        String username = backAdminLoginDTO.getUsername();
        String password = backAdminLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        BlogAdmin blogAdmin = new BlogAdmin();
        blogAdmin.setUsername(username);
        List<BlogAdmin> blogAdmins = blogAdminMapper.queryAllByLimit(blogAdmin, null);
        //2、处理各种异常情况（用户名不存在、密码不对）
        if (blogAdmins == null || blogAdmins.size() == 0) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_NOT_FOUND);  // 这个异常被抛出之后，后面的代码不会再执行 TODO 为什么这里的异常会被捕获到呢？
        }
        // 用户存在
        blogAdmin = blogAdmins.get(0);

        // 密码比对，对前端传过来的明文密码进行md5加密处理，然后在和数据库中的密码比对，数据库中存储的密码都是md加密之后的
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(blogAdmin.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、封装返回的响应数据
        // 现在blogUser是存在的一条数据，就是说用户名存在，密码也正确
        // 下面开始生成token字符串
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, blogAdmin.getId());
        String token = JwtUtil.createJwt(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);  // token是由三个信息编码而来的，存放到token变量中。token也称为jwt令牌

        // 返回响应
        BackAdminLoginVO backAdminLoginVO = BackAdminLoginVO
                .builder()
                .id(blogAdmin.getId())
                .token(token)
                .username(blogAdmin.getUsername())
                .role(blogAdmin.getRole())
                .build();
        return backAdminLoginVO;
    }

    public BackAdminInfoVO getUserInfoById(Integer id) {
        BlogAdmin blogAdmin = blogAdminMapper.queryById(id);
        BackAdminInfoVO backAdminInfoVO = new BackAdminInfoVO();
        BeanUtils.copyProperties(blogAdmin, backAdminInfoVO);
        return backAdminInfoVO;
    }


    public BackAdminLoginVO register(BackAdminRegisterDTO backAdminRegisterDTO) {
        String username = backAdminRegisterDTO.getUsername();

        // 1、根据用户名查询数据库中的数据
        BlogAdmin admin = new BlogAdmin();
        admin.setUsername(username);
        List<BlogAdmin> blogAdmins = blogAdminMapper.queryAllByLimit(admin, null);
        if (blogAdmins != null && blogAdmins.size() != 0) {
            throw new UsernameExistException(MessageConstant.USERNAME_EXIST);  // 用户名已存在
        }

        // 2.待注册的用户名不存在。才进行下面的注册
        BlogAdmin blogAdmin = new BlogAdmin();
        blogAdmin.setUsername(backAdminRegisterDTO.getUsername());
        blogAdmin.setPassword(DigestUtils.md5DigestAsHex(backAdminRegisterDTO.getPassword().getBytes()));
        blogAdmin.setNickName(backAdminRegisterDTO.getNickName());
        blogAdmin.setRole(1);
        blogAdminMapper.insert(blogAdmin);
        System.out.println(blogAdmin.getId());
        return BackAdminLoginVO.builder()
                .username(blogAdmin.getUsername())
                .build();
    }

    public void updateAdminInfo(BackAdminInfoDTO backAdminInfoDTO) {
        BlogAdmin blogAdmin = new BlogAdmin();
        blogAdmin.setId(backAdminInfoDTO.getId());
        blogAdmin.setNickName(backAdminInfoDTO.getNickName());
        blogAdmin.setAvatar(backAdminInfoDTO.getAvatar());
        blogAdminMapper.update(blogAdmin);
    }


    /************************* 代码生成 *************************/
    /**
     * 代码生成-通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public BlogAdmin queryById(Integer id) {
        return this.blogAdminMapper.queryById(id);
    }

    /**
     * 代码生成-分页查询
     *
     * @param blogAdmin   筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    public Page<BlogAdmin> queryByPage(BlogAdmin blogAdmin, PageRequest pageRequest) {
        long total = this.blogAdminMapper.count(blogAdmin);
        return new PageImpl<>(this.blogAdminMapper.queryAllByLimit(blogAdmin, pageRequest), pageRequest, total);
    }

    /**
     * 代码生成-新增数据
     *
     * @param blogAdmin 实例对象
     * @return 实例对象
     */
    public BlogAdmin insert(BlogAdmin blogAdmin) {
        this.blogAdminMapper.insert(blogAdmin);
        return blogAdmin;
    }

    /**
     * 代码生成-修改数据
     *
     * @param blogAdmin 实例对象
     * @return 实例对象
     */
    public BlogAdmin update(BlogAdmin blogAdmin) {
        this.blogAdminMapper.update(blogAdmin);
        return this.queryById(blogAdmin.getId());
    }

    /**
     * 代码生成-通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.blogAdminMapper.deleteById(id) > 0;
    }

}
