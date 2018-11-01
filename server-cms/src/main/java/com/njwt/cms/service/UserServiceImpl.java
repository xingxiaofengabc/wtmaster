package com.njwt.cms.service;

import com.github.pagehelper.PageHelper;
import com.njwt.cms.api.service.UserService;
import com.njwt.cms.core.dao.UserDao;
import com.njwt.cms.entity.UserEntity;
import com.njwt.cms.mapper.UserMapper;
import com.njwt.common.jwt.JWTResponseData;
import com.njwt.common.jwt.JWTSubject;
import com.njwt.common.jwt.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

@Service
public class UserServiceImpl  {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDao userDao;

    public String hello() {
        System.err.println("hello feign ... ");
        return "hello feign!";
    }


    public String hi() {
        System.err.println("hi feign ... ");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hi feign!";
    }


    public String getUserInfo(@RequestParam(value = "username") String username)
    {
        return  (String)this.get(username);
    }

    /**
     * describe: 用户登录
     * @param
     * @return
     * @author 邢晓峰
     * creat_date: 2018/10/19
     * creat_time: 9:24
     **/

    public JWTResponseData login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password){
        JWTResponseData responseData = null;
        String jwtToken="";
        // redis 数据是否存在
        if(get(username)==null){
            JWTSubject subject = new JWTSubject(username);
            jwtToken = JWTUtils.createJWT(UUID.randomUUID().toString(), "njwt-jwt",
                    JWTUtils.generalSubject(subject), 10*60*1000);
            responseData = new JWTResponseData();
            responseData.setCode(200);
            responseData.setData(null);
            responseData.setMsg("登录成功");
            responseData.setToken(jwtToken);

            //设置redis缓存
            set(username,username);
        }else{
            responseData = new JWTResponseData();
            responseData.setCode(500);
            responseData.setData(null);
            responseData.setMsg("用户已登录");
            responseData.setToken(null);
        }
        return responseData;
       // return responseData;
    }

    public Object testAll(@RequestHeader String authtoken) {
        return "1";
    }
    /** ---------------------------------------    mybaids  操作方法                 -----------------------------------------  **/
    public List<UserEntity> getList(){
       return userMapper.selectAll();
    }

    public void addUser(UserEntity userEntity){
        if(userEntity!=null)
            userMapper.insert(userEntity);

    }


    public void addUserList(List<UserEntity> userEntityList){
        userMapper.insertList(userEntityList);
    }

    public void updateUser(UserEntity userEntity){
        userMapper.updateByPrimaryKey(userEntity);
    }

    public void deleteUser(int code){
        userMapper.deleteByPrimaryKey(code);
    }
    public void getUser(UserEntity userEntity){

        userMapper.selectByExample(userEntity);
    }
    public List<UserEntity> selectByPage() {
        PageHelper.offsetPage(1, 1);
        List<UserEntity> dbUserList = userMapper.select(null);
        return dbUserList;
    }






//------------------------------- redis server --------------------------------------------------------------//
    public void set(String key, String value) {
        this.userDao.set(key, value);
    }


    public void set(String key, Object value) {
        this.userDao.set(key, value);
    }


    public Object get(String key) {
        return this.userDao.get(key);
    }


    public long ttl(String key) {
        return this.userDao.ttl(key);
    }


    public void expire(String key, long times, TimeUnit timeUnit) {
        this.userDao.expire(key, times, timeUnit);
    }


}
