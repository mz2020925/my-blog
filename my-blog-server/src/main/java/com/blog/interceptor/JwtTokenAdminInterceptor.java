package com.blog.interceptor;

import com.blog.constant.JwtClaimsConstant;
import com.blog.context.BaseContext;
import com.blog.properties.JwtProperties;
import com.blog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     * 注意：这个方法执行时间在请求到达Controller方法之前，也就是说所有“请求Controller”的请求都会被这个方法拦截。
     * 但是登录操作，访问登录的Controller方法并没有被拦截，为什么呢？因为在类WebMvcConfiguration注册这个拦截器的时候将登录接口排除在外，不拦截登录接口的请求。
     * 在通过Swagger测试的时候，如果不先登录获取一个token（它是一个存储着jwt令牌的变量）然后把它放到请求头中，也是会响应401的。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法（动态方法就是Controller方法），直接放行
            return true;
        }

        // 1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 2.校验令牌
        try {
            log.info("管理端token校验...");
            Claims claims = JwtUtil.parseJwt(jwtProperties.getAdminSecretKey(), token);
            String key = JwtClaimsConstant.ADMIN_ID;
            String value = claims.get(key).toString();
            Integer adminId = Integer.valueOf(value);  // 解析得到token中包含的claims的 值

            // log.info("adminId: {}", adminId);
            BaseContext.setCurrentId(adminId);
            // 3.校验通过，放行，执行后面的controller方法
            log.info("管理端token校验通过");
            return true;
        }catch (Exception e){
            // 4.校验未通过，响应401状态码 -- 401就是代表前端请求头中的token字符串校验未通过
            log.error("管理端token校验通过");
            response.setStatus(401);
            return false;  // 不放行，不执行controller，直接返回状态码是401的响应 -- 但是这里没有写response.getWriter.write()方法，如何实现返回响应呢
        }
    }

}
