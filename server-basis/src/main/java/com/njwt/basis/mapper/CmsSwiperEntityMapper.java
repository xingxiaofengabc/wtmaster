package com.njwt.basis.mapper;

import com.njwt.basis.entity.CmsSwiperEntity;
import com.njwt.basis.entity.CmsSwiperEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsSwiperEntityMapper {
    int countByExample(CmsSwiperEntityExample example);

    int deleteByExample(CmsSwiperEntityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CmsSwiperEntity record);

    int insertSelective(CmsSwiperEntity record);

    List<CmsSwiperEntity> selectByExample(CmsSwiperEntityExample example);

    CmsSwiperEntity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CmsSwiperEntity record, @Param("example") CmsSwiperEntityExample example);

    int updateByExample(@Param("record") CmsSwiperEntity record, @Param("example") CmsSwiperEntityExample example);

    int updateByPrimaryKeySelective(CmsSwiperEntity record);

    int updateByPrimaryKey(CmsSwiperEntity record);
}