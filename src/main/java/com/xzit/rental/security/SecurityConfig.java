package com.xzit.rental.security;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Resource
    private LoginSucdessHandler loginSucdessHandler;
    @Resource
    private LoginFailHandler loginFailHandler;
    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;
    @Resource
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint;
    @Resource
    private CustomerUserDetailsService customerUserDetailsService;
    @Resource
    private VerifyTokenFilter verifyTokenFilter;
    /*@Resource
    private final PasswordEncoder passwordEncoder;*/


    /*@Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
                          LoginSucdessHandler loginSucdessHandler,
                          LoginFailHandler loginFailHandler,
                          CustomerAnonymousEntryPoint customerAnonymousEntryPoint,
                          CustomerAccessDeniedHandler customerAccessDeniedHandler,
                          CustomerUserDetailsService customerUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.loginSucdessHandler = loginSucdessHandler;
        this.loginFailHandler = loginFailHandler;
        this.customerAnonymousEntryPoint = customerAnonymousEntryPoint;
        this.customerAccessDeniedHandler = customerAccessDeniedHandler;
        this.customerUserDetailsService = customerUserDetailsService;
    }*/

    /**
     * 配置安全过滤链
     *
     * @param http 用于配置HttpSecurity的接口
     * @return 返回构建好的SecurityFilterChain对象
     * @throws Exception 如果配置过程中发生错误，则抛出异常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http.addFilterBefore(verifyTokenFilter,
                UsernamePasswordAuthenticationFilter.class);
        // 注入自定义密码编码器
        /*http.authenticationManagerBuilder()
                .userDetailsService(customerUserDetailsService)
                .passwordEncoder(passwordEncoder);*/
// 登录前过滤配置
        http.formLogin()
                .loginProcessingUrl("/rental/user/login") // 设置登录处理URL
                .successHandler(loginSucdessHandler) // 设置登录成功处理器
                .failureHandler(loginFailHandler) // 设置登录失败处理器
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置会话创建策略为无状态
                .and()
                .authorizeHttpRequests() // 授权请求配置
                .requestMatchers("/rental/user/login") // 匹配登录请求
                .permitAll() // 允许所有请求访问
                .anyRequest().authenticated() // 任何其他请求需要认证
                .and()
                .exceptionHandling() // 异常处理配置
                .authenticationEntryPoint(customerAnonymousEntryPoint) // 设 置未认证入口点
                .accessDeniedHandler(customerAccessDeniedHandler) // 设置访问 拒绝处理器
                .and()
                .cors() // 跨域配置
                .and()
                .csrf().disable() // 关闭CSRF保护 跨站请求伪造 是一种网络攻击
                .userDetailsService(customerUserDetailsService);// 设置用户详情服务


        return http.build(); // 构建并返回安全过滤链
    }


}
