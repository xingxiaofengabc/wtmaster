package com.njwt.cms.client.controller;

import com.njwt.cms.api.service.UserService;
import com.njwt.cms.client.feign.FeignClientUser;
import com.njwt.cms.client.model.UserModel;
import com.njwt.common.jwt.JWTResponseData;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
   @Resource
   private FeignClientUser feignClientUser;

   @RequestMapping(value = "/hello",method = {RequestMethod.GET})
    @ResponseBody
    public String hello(){
        return feignClientUser.hello();
    }

    @RequestMapping(value = "/hi",method = {RequestMethod.GET})
    @ResponseBody
    public String hi(){
        return feignClientUser.hi();
    }

    @RequestMapping(value = "/tologin",method = {RequestMethod.GET})
    public String toLogin(){
        return "/login_v2";
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    @ResponseBody
    public JWTResponseData Login(@RequestParam("username") String username, @RequestParam("password") String password){

        JWTResponseData  jwtResponseData= feignClientUser.login( username,password);
        System.err.println("jwtResponseData:"+jwtResponseData);
        return  jwtResponseData;
    }
    @RequestMapping(value = "/testAll",method = {RequestMethod.GET})
    @ResponseBody
    public String testAll(@RequestHeader("authtoken") String authtoken, HttpServletResponse response,HttpServletRequest request){
        System.err.println("authtoken:"+authtoken);
      String obj=  feignClientUser.testAll(authtoken);
        System.err.println(request.getHeaders("authtoken"));
        System.err.println(response.getHeaders("authtoken"));

      return  obj;
    }
    /**
     * describe: Ê×Ò³
     * @param
     * @return
     * @author ÐÏÏþ·å
     * creat_date: 2018/10/31
     * creat_time: 12:17
     **/

    @RequestMapping(value = "/index",method = {RequestMethod.GET})
    public String index(){
       return "/index";
    }
}
