package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BackArticleTitleExistDTO implements Serializable {
    private String articleTitle;
    private Integer id;
}
