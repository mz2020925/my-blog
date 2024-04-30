package com.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FrontArticlesByCTIdDTO implements Serializable {
    private Integer current;
    private Integer size;
    private Integer id;
}
