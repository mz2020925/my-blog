package com.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BlogUser)实体类
 *
 * @author makejava
 * @since 2024-01-03 16:21:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAdmin implements Serializable {
    private static final long serialVersionUID = -35820423487174858L;

    private Integer id;
    /**
     * 账号，唯一
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户角色 1 管理员 2 普通用户
     */
    private Integer role;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String avatar;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    /**
     * 用户QQ 用于联系
     */
    private String qq;
    /**
     * ip属地
     */
    private String ip;

}

