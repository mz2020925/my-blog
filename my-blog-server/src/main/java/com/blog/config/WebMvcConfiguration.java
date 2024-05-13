package com.blog.config;

import com.blog.interceptor.JwtTokenAdminInterceptor;
import com.blog.json.JacksonObjectMapper;
import com.blog.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    @Autowired
    private JwtTokenAdminInterceptor jwtTokenAdminInterceptor;
    @Autowired
    private MinioProperties minioProperties;

    /**
     * 注册自定义拦截器：jwt校验
     *
     * @param registry
     */
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        // 添加了两个拦截器，这两个拦截器会拦截不同请求url的请求
        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/article/**")
                .addPathPatterns("/category/**")
                .addPathPatterns("/file/**")
                .addPathPatterns("/tag/**")
                .excludePathPatterns("/admin/login")  // 不拦截这个Controller方法
                .excludePathPatterns("/admin/register");  // 不拦截这个Controller方法
        // registry.addInterceptor(jwtTokenUserInterceptor)
        //         .addPathPatterns("/user/**")
        //         .excludePathPatterns("/user/user/login")  // 不拦截这个Controller方法
        //         .excludePathPatterns("/user/shop/status");  // 不拦截这个Controller方法,为什么admin管理端不排除这个Controller方法，因为用户端是小程序，在登录之前就应该展示出店铺的登录状态。
    }

    /**
     * 这个就是使用Swagger需要的配置类
     * 通过knife4j生成接口文档
     * 下面是在本配置类中加入knife4j相关配置
     *
     * @return
     */
    @Bean
    public Docket docket1() {
        log.info("开始生成Swagger管理端接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("博客项目接口文档")
                .version("2.0")
                .description("博客项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("管理端接口")
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.blog.controller.back"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    @Bean
    public Docket docket2() {
        log.info("开始生成Swagger用户端接口文档...");
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("博客项目接口文档")
                .version("2.0")
                .description("博客项目接口文档")
                .build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户端接口")
                .apiInfo(apiInfo)
                .select()
                //指定生成接口需要扫描的包
                .apis(RequestHandlerSelectors.basePackage("com.blog.controller.front"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }


    /**
     * 设置Swagger静态资源映射
     * 这些静态资源就是后端自己测试访问到的接口文档页面
     *
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始设置静态资源映射...");
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        // classpath指的就是项目编译打包后的WEB-INF目录下的classes，包含src/main/下的java和resources这两个文件夹
        // classpath指的就是项目编译打包后的WEB-INF目录下的classes，包含src/main/下的java和resources这两个文件夹
        // 后端接口自己测试访问Swagger http://localhost:8080/doc.html
        // Swagger会自动的把接口文档页面资源放到"classpath:/META-INF/resources/" 和 "classpath:/META-INF/resources/webjars/"下面
    }

    /**
     * 扩展MVC框架的消息转换器：这里应该解决的是将返回响应的对象转换成json字符串中存在的问题
     * 由于分页查询中的时间格式不正确，所以有两种解决方案，这里是第二种
     * 在WebMvcConfiguration中扩展 SpringMVC的消息转换器，实现统一对日期类型进行格式化处理。
     * 其实就是我们现在使用的是Spring MVC框架，这个框架本身有一个消息转换器，下面我们要扩展这个消息转换器，从而使它能够将返回的响应消息中的时间格式转换正确。
     * 什么是转换正确，就是后端返回给前端的json文本中时间格式应该是
     * "createTime": [
     * 2023-09-27 11:40:27
     * ],
     * 而不是
     * "createTime": [
     * 2023,
     * 9,
     * 26,
     * 15,
     * 9,
     * 30
     * ],
     * 第一种方式，比如是在类OrdersPageQueryDTO的时间属性"private LocalDateTime beginTime;"上面加上注解：
     *
     * @param converters
     * @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     * <p>
     * 为什么推荐第二种方式，因为第二种方式是扩展消息转换器，对时间属性的序列化、反序列化进行了配置（实现了统一配置）。
     */
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("开始扩展SpringMVC消息转换器，修正分页查询中的时间格式...");
        // 创建一个消息转换器对象
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // 设置对象转换器，可以将java对象转换为json字符串
        converter.setObjectMapper(new JacksonObjectMapper());
        // 将我们自己的转换器放入spring MVC框架的容器中
        converters.add(0, converter);
    }

    /**
     * 本项目中使用一项技术MinIO ，来搭建存储服务器
     * 本函数返回 存储服务器 的一个Client连接。就类似于建立 MySQL 的一个连接一样。
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {
        log.info("开始创建minio存储服务器的一个Client连接...");
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

}
