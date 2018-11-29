package com.njwt.cms.mapper;

import com.njwt.cms.entity.SwiperEntity;
import com.njwt.cms.entity.SwiperEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SwiperEntityMapper {
    int countByExample(SwiperEntityExample example);

    int deleteByExample(SwiperEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SwiperEntity record);

    int insertSelective(SwiperEntity record);

    List<SwiperEntity> selectByExample(SwiperEntityExample example);

    SwiperEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SwiperEntity record, @Param("example") SwiperEntityExample example);

    int updateByExample(@Param("record") SwiperEntity record, @Param("example") SwiperEntityExample example);

    int updateByPrimaryKeySelective(SwiperEntity record);

    int updateByPrimaryKey(SwiperEntity record);
}