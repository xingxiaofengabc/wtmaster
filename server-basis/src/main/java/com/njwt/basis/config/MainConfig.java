package com.njwt.basis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc        //启用了spring mvc
@Configuration        // 让spring boot 项目启动时识别当前配置类
@ComponentScan({"com.njwt.basis.*"})	//
@MapperScan(basePackages = "com.njwt.basis.mapper")
public class MainConfig {
}
