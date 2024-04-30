package com.blog.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (BlogConfig)实体类
 *
 * @author makejava
 * @since 2023-12-30 19:01:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogConfig implements Serializable {
    private static final long serialVersionUID = -75063334633623748L;
    
    private Integer id;
    /**
     * 博客名称
     */
    private String blogName;
    /**
     * 博客头像
     */
    private String blogAvatar;
    /**
     * 博客头像背景图
     */
    private String avatarBg;
    /**
     * 个人签名
     */
    private String personalSay;
    /**
     * 博客公告
     */
    private String blogNotice;
    /**
     * qq链接
     */
    private String qqLink;
    /**
     * 微信链接
     */
    private String weChatLink;
    /**
     * github链接
     */
    private String githubLink;
    /**
     * git_ee链接
     */
    private String gitEeLink;
    /**
     * bilibili链接
     */
    private String bilibiliLink;
    /**
     * 博客被访问的次数
     */
    private Long viewTime;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
    /**
     * 微信群图片
     */
    private String weChatGroup;
    /**
     * qq群图片
     */
    private String qqGroup;
    /**
     * 微信收款码
     */
    private String weChatPay;
    /**
     * 支付宝收款码
     */
    private String aliPay;

}

