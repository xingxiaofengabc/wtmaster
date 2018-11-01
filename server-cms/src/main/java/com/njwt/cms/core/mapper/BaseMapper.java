package com.njwt.cms.core.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.provider.SpecialProvider;
import java.util.List;
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
    @Options(useGeneratedKeys = true, keyProperty = "code")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);
}
