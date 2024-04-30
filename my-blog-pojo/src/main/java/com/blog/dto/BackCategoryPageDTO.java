package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackCategoryPageDTO implements Serializable {
    private Integer current;
    private Integer size;
    private String categoryName;
}
