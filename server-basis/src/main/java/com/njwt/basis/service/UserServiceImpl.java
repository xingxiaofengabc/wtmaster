package com.njwt.basis.service;

import com.github.pagehelper.PageHelper;
import com.njwt.basis.entity.UserEntity;
import com.njwt.basis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl {
    @Resource
    private UserMapper userMapper;

    public List<UserEntity> getList(){
        return userMapper.selectAll();
    }

    public void addUser(UserEntity userEntity){
        if(userEntity!=null)
            userMapper.insert(userEntity);

    }

    public void updateUser(UserEntity userEntity){
        userMapper.updateByPrimaryKey(userEntity);
    }

    public void deleteUser(int code){

        userMapper.deleteByPrimaryKey(code);
    }
    public void getUser(UserEntity userEntity){

        userMapper.select(userEntity);
    }
    public List<UserEntity> selectByPage() {
        PageHelper.offsetPage(1, 1);
        List<UserEntity> dbUserList = userMapper.select(null);
        return dbUserList;
    }






}
