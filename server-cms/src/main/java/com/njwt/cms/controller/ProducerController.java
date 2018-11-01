package com.njwt.cms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@Api("swaggerDemoController相关的api")
public class ProducerController {

   // @ApiOperation(value = "添加")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = {			MediaType.APPLICATION_JSON_UTF8_VALUE })
    public @ResponseBody String addUser(@ApiParam(value = "id", required = true) @RequestParam(value = "userId", required = true) Long userId,
                                        @ApiParam(value = "角色编号", required = true)
    @RequestParam(value = "userCode", required = true) String userCode, @ApiParam(value = "角色名称", required = true)
    @RequestParam(value = "userName", required = true) String userName) {
        return "";
    }

    @RequestMapping(value = "/hello", method = {RequestMethod.GET})
    @ResponseBody
    public String hello() {
        System.err.println("hello feign ... ");
        return "hello feign!";
    }

    @RequestMapping(value = "/hi", method = {RequestMethod.GET})
    public String hello2() {
        System.err.println("hi feign ... ");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hi feign!";
    }
}
