package com.xzit.rental.security;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//自定义密码编码器
//@Component
public class CustomerPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {//加密
        return rawPassword.toString();
    }
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword)//匹配
    {
        return StrUtil.equals(encode(rawPassword),encodedPassword);
    }
}

