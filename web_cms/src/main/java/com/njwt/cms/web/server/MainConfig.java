package com.njwt.cms.web.server;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc        //启用了spring mvc
@Configuration        // 让spring boot 项目启动时识别当前配置类
@ComponentScan({"com.njwt.cms"})	//初始化类
public class MainConfig {
}
