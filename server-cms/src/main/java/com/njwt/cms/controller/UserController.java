package com.njwt.cms.controller;

import com.njwt.cms.api.service.UserService;
import com.njwt.cms.service.UserServiceImpl;
import com.njwt.common.jwt.JWTResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
public class UserController implements UserService {

    Logger logerr= LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserServiceImpl userServiceImpl;

   // @RequestMapping(value = "/server-cms/hello",method = {RequestMethod.GET})
    @ResponseBody
    public String hello(){
       return userServiceImpl.hello();
    }

    //@RequestMapping(value = "/server-cms/hi",method = {RequestMethod.GET})
    @ResponseBody
    public String hi(){
        return  userServiceImpl.hi();
    }
    /**
     * describe: 获取redis用户信息
     * @param  tokenValue 用户名称
     * @return
     * @author 邢晓峰
     * creat_date: 2018/10/19
     * creat_time: 16:55
     **/
    @RequestMapping(value = "/getuserinfo",method = {RequestMethod.GET})
    @ResponseBody
    public String getUserInfo(@RequestParam("tokenValue") String tokenValue)
    {
        return  (String)userServiceImpl.get(tokenValue);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.GET})
    @ResponseBody
    public JWTResponseData login(@RequestParam("username") String username, @RequestParam("password") String password){
        JWTResponseData responseData = null;
        responseData= userServiceImpl.login(username,password);
        return responseData;
    }
    @RequestMapping(value = "/testAll",method = {RequestMethod.GET})
    @ResponseBody
    public String testAll(@RequestHeader("authtoken") String authtoken){
        return "1";
 /*
        String token = request.getHeader("Authorization");
        JWTResult result = JWTUtils.validateJWT(token);

        JWTResponseData responseData = new JWTResponseData();

        if(result.isSuccess()){
            responseData.setCode(200);
            responseData.setData(result.getClaims().getSubject());
            // 重新生成token，就是为了重置token的有效期。
            String newToken = JWTUtils.createJWT(result.getClaims().getId(),
                    result.getClaims().getIssuer(), result.getClaims().getSubject(),
                    1*60*1000);
            responseData.setToken(newToken);
            return responseData;
        }else{
            responseData.setCode(500);
            responseData.setMsg("用户未登录");
            return responseData;
        }*/

    }
}

