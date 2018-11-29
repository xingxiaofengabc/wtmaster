package com.njwt.cms.service;

import com.github.pagehelper.PageHelper;
import com.njwt.cms.core.mapper.BaseMapper;
import com.njwt.cms.entity.SwiperEntity;
import com.njwt.cms.entity.UserEntity;

import javax.annotation.Resource;
import java.util.List;

public abstract class BaseServiceImpl<T> {

   @Resource
   public BaseMapper mapper;

    public List<T> getList(){
        return mapper.selectAll();
    }

    public void add(T entity){
        if(entity!=null)
            mapper.insert(entity);

    }


    public void addList(List<T> entityList){
        mapper.insertList(entityList);
    }

    public void update(T entity){
        mapper.updateByPrimaryKey(entity);
    }

    public void delete(int code){
        mapper.deleteByPrimaryKey(code);
    }
    public void get(T entity){

        mapper.selectByExample(entity);
    }
    public List<T> selectByPage() {
        PageHelper.offsetPage(1, 1);
        List<T> list = mapper.select(null);
        return list;
    }



}
