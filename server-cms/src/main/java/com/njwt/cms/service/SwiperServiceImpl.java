package com.njwt.cms.service;

import com.github.pagehelper.PageHelper;
import com.njwt.cms.entity.SwiperEntity;
import com.njwt.cms.entity.UserEntity;
import com.njwt.cms.mapper.SwiperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SwiperServiceImpl {



  @Resource
  private SwiperMapper swiperMapper;
  /** ---------------------------------------    mybaids  操作方法                 -----------------------------------------  **/
    public List<SwiperEntity> getList(){
        return swiperMapper.selectAll();
    }

    public int addSwiper(SwiperEntity swiperEntity){
        if(swiperEntity!=null)
         return    swiperMapper.insert(swiperEntity);
         return 0;
    }


    public int addSwiperList(List<SwiperEntity> entityList){
        return swiperMapper.insertList(entityList);
    }

    public int updateSwiper(SwiperEntity swiperEntity){
       return swiperMapper.updateByPrimaryKey(swiperEntity);
    }

    public int deleteSwiper(int code){
       return  swiperMapper.deleteByPrimaryKey(code);
    }
    public SwiperEntity getSwiper(SwiperEntity swiperEntity){

        return swiperMapper.selectOne(swiperEntity);
    }
    public List<SwiperEntity> selectByPage(int page, int pageSize){
        PageHelper.offsetPage(page, pageSize);
        List<SwiperEntity> dbSwiperList = swiperMapper.select(null);
        return dbSwiperList;
    }

    public List<SwiperEntity> selectAll(){
      return  swiperMapper.selectAll();
    }






}
