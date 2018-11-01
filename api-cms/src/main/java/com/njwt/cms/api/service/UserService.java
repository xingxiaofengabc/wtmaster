package com.njwt.cms.api.service;
import com.njwt.common.jwt.JWTResponseData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


public interface UserService {

    @RequestMapping(value = "/getuserinfo", method = {RequestMethod.GET})
    public String getUserInfo(@RequestParam("tokenValue") String tokenValue);

    @RequestMapping(value = "/server-cms/login",method = {RequestMethod.GET})
    public JWTResponseData login(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/server-cms/testAll",method = {RequestMethod.GET})
    public String testAll(@RequestHeader("authtoken") String authtoken);

    @RequestMapping(value = "/server-cms/hello",method = {RequestMethod.GET})
    public String hello();

    @RequestMapping(value = "/server-cms/hi",method = {RequestMethod.GET})
    public String hi();
}
