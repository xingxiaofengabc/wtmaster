package com.njwt.basis.controller;

import com.njwt.basis.entity.UserEntity;
import com.njwt.basis.service.UserServiceImpl;
import com.njwt.common.jwt.JWTResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

@RestController
@Api("用户信息服务")
public class UserController {

    Logger logerr= LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserServiceImpl userServiceImpl;

    @ApiOperation(value = "selectAll", notes = "分页-用户信息")

    @GetMapping(value="selectAll")

    public List<UserEntity> selectByPage() {

     return    userServiceImpl.selectByPage();

    }

    @RequestMapping(value="selectAll", method=RequestMethod.POST)
    public List<UserEntity> selectAll(){

        return  userServiceImpl.getList();

    }
}
