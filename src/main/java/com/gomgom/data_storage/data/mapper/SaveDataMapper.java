package com.gomgom.data_storage.data.mapper;

import com.gomgom.data_storage.data.model.SaveData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaveDataMapper {
    int insert(SaveData record);

    int insertSelective(SaveData record);
}
