package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackAdminInfoDTO implements Serializable {
    private Integer id;
    private String nickName;
    private String avatar;
    private String password;
}
