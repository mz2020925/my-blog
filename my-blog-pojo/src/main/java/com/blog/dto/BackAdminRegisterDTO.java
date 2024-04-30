package com.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackAdminRegisterDTO implements Serializable {
    private String username;
    private String password;
    private String nickName;
}
