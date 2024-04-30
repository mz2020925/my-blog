package com.blog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员登录时传递的数据模型")
public class BackAdminLoginDTO implements Serializable {
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;
}
