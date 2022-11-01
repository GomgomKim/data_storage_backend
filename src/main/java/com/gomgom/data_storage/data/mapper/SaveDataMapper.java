package com.gomgom.data_storage.data.mapper;

import com.gomgom.data_storage.data.model.SaveDataString;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SaveDataMapper {
    int insert(SaveDataString record);

    int insertSelective(SaveDataString record);

    int insertMultiRow(HashMap<String, List<HashMap<String ,String>>> dataParam);
}
