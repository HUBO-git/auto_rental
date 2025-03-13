package com.xzit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.xzit.rental")
@MapperScan("com.xzit.rental.mapper")//在启动类上，加入对Mapper的扫描
public class AutoRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoRentalApplication.class, args);
    }

}
