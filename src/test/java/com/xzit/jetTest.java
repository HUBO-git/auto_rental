package com.xzit;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.jwt.JWTPayload;
import com.xzit.rental.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class jetTest {
    @Resource
    private JwtUtils jwtUtils;
    @Test
    void jew(){
        JWTPayload jwtPayload = JwtUtils.parseToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE3NDE0MTc4MTIsImV4cCI6MTc0MTQxOTYx" +
                "MiwidXNlcmlkIjo2LCJpYXQiOjE3NDE0MTc4MTIsInVzZXJuYW1lIjoibGlzaSJ9.43QEQ001hHUx4RGP3n5lIO94M5q0xn6Yu2UUBvAMSYg");
        NumberWithFormat claim = (NumberWithFormat)
                jwtPayload.getClaim(JWTPayload.EXPIRES_AT);
        long expireTime= Convert.toDate(claim).getTime();
        System.out.println(expireTime);
    }
}
