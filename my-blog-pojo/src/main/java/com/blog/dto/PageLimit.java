package com.blog.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageLimit implements Serializable {
    private Integer current;
    private Integer size;
    private Integer offset;
    private Integer pageSize;

    public PageLimit(Integer current, Integer size) {
        this.current = current;
        this.size = size;
        this.offset = (current - 1) * size;
        this.pageSize = size;
    }
}
