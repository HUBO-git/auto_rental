package com.xzit.rental.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {
    //jwt秘钥
    public static final String SECRET_KEY = "coder_hu";
    //过期时间
    public static final Long EXPIRE_TIME = 1000L * 60 * 30;

    /**
     * 创建一个JWT Token。
     *
     * @param payload 包含Token有效载荷的Map，将被添加签发时间、过期时间和生效时间。
     *
     * @return 生成的JWT Token字符串。
     */
    public static String createToken(Map<String, Object> payload) {
        DateTime now = DateTime.now();
        Long l=now.getTime();
        log.info("签发时间：{}",now);
        DateTime newTime = new DateTime(l+EXPIRE_TIME);
        log.info("过期时间：{}",newTime);
        //设置token的签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //设置token的过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //设置token的生效时间,确保签发后，立即生效
        payload.put(JWTPayload.NOT_BEFORE, now);
        return JWTUtil.createToken(payload, SECRET_KEY.getBytes());
    }



    /**
     * 解析JWT Token并返回其Payload。
     *
     * @param token 待解析的JWT Token字符串。
     * @return 解析出的JWT Payload对象。
     * @throws RuntimeException 如果Token验证失败或已过期，则抛出异常。
     */
    public static JWTPayload parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);//解析传入的token
        log.info("jwt：{}",jwt);
        if (!jwt.setKey(SECRET_KEY.getBytes()).verify()) {//检查token是否被篡改
            throw new RuntimeException("token异常");
        }
        if (!jwt.validate(0)) {//检查token是否过期
            throw new RuntimeException("token过期");
        }
        return jwt.getPayload();

    }


}
