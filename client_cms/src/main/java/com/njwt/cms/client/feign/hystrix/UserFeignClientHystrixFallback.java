package com.njwt.cms.client.feign.hystrix;

import com.njwt.cms.client.feign.FeignClientUser;
import com.njwt.common.jwt.JWTResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserFeignClientHystrixFallback implements FeignClientUser {
    @Override
    public String hello() {
        System.err.println("---------hello方法的降级策略---------");
        return "---------hello方法的降级策略---------";
    }

    @Override
    public String hi() {
        System.err.println("---------hi方法的降级策略---------");
        return "---------hi方法的降级策略---------";
    }
    @Override
    public JWTResponseData login(String username, String password){
        System.err.println("---------login方法的降级策略---------");
        return null;
    }
    @Override
    public String testAll(@RequestHeader("authtoken") String authtoken){
        return "---------request方法的降级策略---------";
    }
    @Override
    public String getUserInfo(String tokenValue){
        return "---------getUserInfo方法的降级策略---------";
    }


}
