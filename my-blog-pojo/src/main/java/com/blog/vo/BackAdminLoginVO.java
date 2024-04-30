package com.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员登陆返回数据")
public class BackAdminLoginVO implements Serializable {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("角色")
    private Integer role;
}
