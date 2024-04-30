package com.blog.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /**
     * 生成token
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public static String createJwt(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // 指定签名的时候使用的签名算法，也就是header中的token字符串按照什么算法生成
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;

        // 过期时间 = 生成JWT的时间 + 生命周期
        long expiredMillis = System.currentTimeMillis() + ttlMillis;
        Date expiredDate = new Date(expiredMillis);

        // 创建JwtBuilder对象，注意claims这个键值对是传递进来的，键 和 值已经设置好了
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)  // TODO 没看懂后面这个什么声明？？如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的。claims就是一个HashMap<String, Object>，存了一个键值对(JwtClaimsConstant.EMP_ID, employee.getId())
                .signWith(hs256, secretKey.getBytes(StandardCharsets.UTF_8))  // 设置签名使用的签名算法和签名使用的秘钥。secretKey的值：jwtProperties.getAdminSecretKey()
                .setExpiration(expiredDate);
                // 这里没有追加.build(); 因为我们需要的就是Jwts.builder()对象，这里和@Builder注解类似但是是不一样的

        return jwtBuilder.compact();  // 将JwtBuilder对象的属性拼接起来，就是token字符串，返回

    }

    /**
     * token解密
     *
     * @param secretKey jwt秘钥 此秘钥一定要保留好在服务端, 不能暴露出去, 否则sign就可以被伪造, 如果对接多个客户端建议改造成多个
     * @param token     加密后的token
     * @return
     */
    public static Claims parseJwt(String secretKey, String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))  // 设置签名的秘钥
                .parseClaimsJws(token)  // 设置需要解析的token。拿着本地存的密钥去解析token，如果用本地的密钥解不开前端给的token，说明前端的这个请求有问题。如果解析成功，说明没有问题，把解析后的token的Claims那部分返回，即getBody()
                .getBody();

        return claims;
    }


}
