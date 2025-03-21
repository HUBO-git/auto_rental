package com.xzit.rental.security;
import cn.hutool.core.util.StrUtil;
import com.xzit.rental.utils.JwtUtils;
import com.xzit.rental.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import
        org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import
        org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
/**
 * OncePerRequestFilter是spring boot 提供的过滤器抽象类
 * 在Spring security应用广泛，可以用来过滤请求
 * 这个过滤器通常被 用于继承实现并在每次请求时执行
 */
@Component
class VerifyTokenFilter extends OncePerRequestFilter {
    @Value("${request.login-url}")//可以配置值
    private String loginUrl;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private LoginFailHandler loginFailHandler;
    /**
     * 处理过滤逻辑，用于验证请求的令牌。
     * 如果请求的URL不是登录页面的URL，则进行令牌验证。
     * 如果验证失败，则调用登录失败处理器。
     *
     * @param request HttpServletRequest对象，代表客户端的HTTP请求
     * @param response HttpServletResponse对象，用于向客户端发送HTTP响应
     * @param filterChain FilterChain对象，用于继续或终止过滤器链中的下一个过滤器
     * @throws ServletException 如果处理请求时发生Servlet相关异常
     * @throws IOException 如果处理请求时发生IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws
            ServletException, IOException {
        String url = request.getRequestURI();
// 当请求的URL不是登录页面的URL时，进行令牌验证
        if (!StrUtil.equals(url, loginUrl)) {
            try {
                validateToken(request, response); // 验证令牌
            } catch (AuthenticationException e) {
// 令牌验证失败，调用登录失败处理器处理异常
                loginFailHandler.onAuthenticationFailure(request, response,
                        e);
            }
        }
// 继续过滤器链的处理
        doFilter(request, response, filterChain);
    }
    /**
     * 验证令牌（token）的有效性。
     *
     * @param request HttpServletRequest对象，用于获取请求头或参数中的token。
     * @param response HttpServletResponse对象，用于在验证失败时设置响应状态码。
     * @throws AuthenticationException 如果token验证失败，抛出
    CustomerAuthenticationException异常。
     */
    private void validateToken(HttpServletRequest request,
                               HttpServletResponse response) throws
            AuthenticationException {
// 尝试从请求头获取token，如果不存在则从请求参数中获取
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter("token");
        }
// 如果token为空，则抛出异常
        if (StrUtil.isEmpty(token)) {
            throw new CustomerAuthenticationException("token为空");
        }
        // 从Redis中获取tokenKey对应的值，用于和请求中的token进行比较
        String tokenKey = "token:" + token;
        String tokenValue = redisUtils.get(tokenKey);
// 如果Redis中不存在tokenKey对应的值，则抛出token已过期异常
        if (StrUtil.isEmpty(tokenValue)) {
            throw new CustomerAuthenticationException("token已过期");
        }
// 如果请求中的token和Redis中的token不一致，则抛出token错误异常
        if (!StrUtil.equals(token, tokenValue)) {
            throw new CustomerAuthenticationException("token错误");
        }
// 解析token，获取用户名
        String username = JwtUtils.parseToken(token)
                .getClaim("username").toString();
// 如果无法解析token，则抛出token解析失败异常
        if (StrUtil.isEmpty(username)) {
            throw new CustomerAuthenticationException("token解析失败");
        }
// 根据用户名加载用户详情
        UserDetails userDetails = customerUserDetailsService
                .loadUserByUsername(username);
// 如果用户不存在，则抛出异常
        if (userDetails == null) {
            throw new CustomerAuthenticationException("用户不存在");
        }
// 创建认证信息，并设置到SecurityContextHolder中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
