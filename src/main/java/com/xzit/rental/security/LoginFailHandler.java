package com.xzit.rental.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xzit.rental.utils.Result;
import com.xzit.rental.utils.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    /**
     * 处理登录失败的情况。
     *
     * @param request HttpServletRequest对象，代表客户端的请求
     * @param response HttpServletResponse对象，用于向客户端发送响应
     * @param exception 登录失败抛出的异常对象
     * @throws IOException 如果发生I/O错误
     * @throws ServletException 如果发生Servlet相关错误
     */

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");//设置相应格式
        ServletOutputStream outputStream = response.getOutputStream();
        Integer code = ResultCode.ERROR;
        String errorInfo=null;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
            errorInfo = "账户名或者密码输入错误!";
            code=ResultCode.UNAUTHORIZED;
        } else if (exception instanceof LockedException) {
            errorInfo = "账户被锁定，请联系管理员!";
            code=ResultCode.UNAUTHORIZED;
        } else if (exception instanceof CredentialsExpiredException) {
            errorInfo = "密码过期，请联系管理员!";
            code=ResultCode.UNAUTHORIZED;
        } else if (exception instanceof AccountExpiredException) {
            errorInfo = "账户过期，请联系管理员!";
            code=ResultCode.UNAUTHORIZED;
        } else if (exception instanceof DisabledException) {
            errorInfo = "账户被禁用，请联系管理员!";
            code=ResultCode.UNAUTHORIZED;
        } else if(exception instanceof InternalAuthenticationServiceException){
            code=ResultCode.UNAUTHORIZED;
            errorInfo = exception.getMessage();
        }
        else {
            errorInfo = "登录失败!";
        }
        String result= JSON.toJSONString(Result.fail().setCode(code).setMessage(errorInfo));
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }
}
