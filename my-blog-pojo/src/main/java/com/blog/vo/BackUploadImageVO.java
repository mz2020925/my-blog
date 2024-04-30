package com.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class BackUploadImageVO implements Serializable {
    private String url;
}
