package com.njwt.cms.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc        //启用了spring mvc
@Configuration        // 让spring boot 项目启动时识别当前配置类
@ComponentScan({"com.njwt.cms.*"})	//
@MapperScan(basePackages = "com.njwt.cms.mapper")
public class MainConfig {
}
