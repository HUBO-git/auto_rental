package com.xzit.rental.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * 配置mybatisplus分页插件
 */

public class MybatisPlusConfig {
    /**
     * 创建并配置 MyBatis-Plus 拦截器，主要用于添加分页插件，以支持分页查询功能。
     *
     * @return 配置好的 MyBatis-Plus 拦截器实例
     */
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建一个 MyBatis-Plus 拦截器实例，该拦截器用于管理多个 MyBatis-Plus 内置的拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 向 MyBatis-Plus 拦截器中添加分页拦截器
        // PaginationInnerInterceptor 是 MyBatis-Plus 提供的用于实现分页功能的拦截器
        // DbType.MYSQL 表示使用的数据库类型为 MySQL，拦截器会根据此数据库类型生成相应的分页 SQL 语句
        // 注意：如果需要配置多个插件，分页插件建议最后添加，以确保分页逻辑能正确处理
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        // 返回配置好的 MyBatis-Plus 拦截器实例，该实例可被注册到 Spring 容器中，供其他组件使用
        return interceptor;
    }

}
