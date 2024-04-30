package com.blog;

import com.blog.mapper.BlogArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MyBlogServerApplicationTests {
    @Resource
    private BlogArticleMapper blogArticleMapper;

    @Test
    void contextLoads() {
    }

}
