package com.gomgom.data_storage.api.save_data.service;

import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequest;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateResult;
import com.gomgom.data_storage.data.mapper.SaveDataMapper;
import com.gomgom.data_storage.data.model.SaveData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SaveDataService {
    @Autowired
    SaveDataMapper saveDataMapper;

    @Transactional
    public SaveDataCreateResult createSaveData(SaveDataCreateRequest request) throws Exception {
        SaveData SaveData = new SaveData();

        log.debug("request :" + request);

        SaveDataCreateResult result = saveDataMapper.insertSelective(SaveData) > 0 ? SaveDataCreateResult.SUCCESS : SaveDataCreateResult.FAIL;

        return result;
    }
}
