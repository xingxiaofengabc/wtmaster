package com.njwt.server.wechat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
   private Logger logger= LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/index")
    public String index(){
        logger.info("查看首页");
        return "/index";
    }
}
