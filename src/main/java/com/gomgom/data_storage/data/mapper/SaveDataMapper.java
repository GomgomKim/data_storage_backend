package com.gomgom.data_storage.data.mapper;

import com.gomgom.data_storage.data.model.SaveDataString;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaveDataMapper {
    int insert(SaveDataString record);

    int insertSelective(SaveDataString record);
}
