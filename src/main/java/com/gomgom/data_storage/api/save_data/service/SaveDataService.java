package com.gomgom.data_storage.api.save_data.service;

import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestCsv;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestString;
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
    public SaveDataCreateResult createSaveDataString(SaveDataCreateRequestString request) throws Exception {
        SaveData saveData = new SaveData();
        saveData.setId(request.getId());
        saveData.setFirstname(request.getFirstname());
        saveData.setLastname(request.getLastname());
        saveData.setEmail(request.getEmail());

        log.info("gomgom request :" + request);

        SaveDataCreateResult result = saveDataMapper.insertSelective(saveData) > 0 ? SaveDataCreateResult.SUCCESS : SaveDataCreateResult.FAIL;
        log.info("gomgom result :" + result);

        return result;
    }

    public SaveDataCreateResult createSaveDataCsv(SaveDataCreateRequestCsv request) throws Exception {
//        SaveData saveData = new SaveData();

        log.info("gomgom request :" + request);

//        SaveDataCreateResult result = saveDataMapper.insertSelective(saveData) > 0 ? SaveDataCreateResult.SUCCESS : SaveDataCreateResult.FAIL;
        SaveDataCreateResult result = SaveDataCreateResult.SUCCESS;
        log.info("gomgom result :" + result);
        return result;
    }
}
