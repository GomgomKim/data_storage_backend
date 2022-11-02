package com.gomgom.data_storage.api.save_data.service;

import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestString;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateResult;
import com.gomgom.data_storage.common.util.CsvFileParseUtil;
import com.gomgom.data_storage.data.mapper.SaveDataMapper;
import com.gomgom.data_storage.data.model.SaveDataString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class SaveDataService {
    @Autowired
    SaveDataMapper saveDataMapper;

    @Transactional
    public SaveDataCreateResult createSaveDataString(SaveDataCreateRequestString request) throws Exception {
        SaveDataString saveData = new SaveDataString();
        saveData.setId(request.getId());
        saveData.setFirstname(request.getFirstname());
        saveData.setLastname(request.getLastname());
        saveData.setEmail(request.getEmail());
        SaveDataCreateResult result = saveDataMapper.insertSelective(saveData) > 0 ? SaveDataCreateResult.SUCCESS : SaveDataCreateResult.FAIL;
        return result;
    }

    public SaveDataCreateResult createSaveDataCsv(MultipartFile[] files) throws Exception {
        SaveDataCreateResult result = SaveDataCreateResult.FAIL;

        for (MultipartFile file : files) {
            List<String> fileHeader = getHeaderInCsvFile(file);
            List<List<String>> fileBody = getBodyInCsvFile(file);

            long beforeTime = System.currentTimeMillis();

            List<List<HashMap<String ,String>>> dataParams = makeQueryParamData(fileHeader, fileBody);
            for (List<HashMap<String ,String>> dataParam : dataParams) {
                HashMap<String, List<HashMap<String ,String>>> param = new HashMap<>();
                param.put("multiData", dataParam);
                result = saveDataMapper.insertMultiRow(param) > 0 ? SaveDataCreateResult.SUCCESS : SaveDataCreateResult.FAIL;
                if (result == SaveDataCreateResult.FAIL) {
                    break;
                }
            }

            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime)/1000;
            System.out.println("Insert 시간 (s) : " + secDiffTime);
        }
        return result;
    }

    private List<String> getHeaderInCsvFile(MultipartFile file) {
        try {
            return new CsvFileParseUtil().getHeader(file.getInputStream());
        } catch (IOException e) {
            log.error("CSV file header parsing error : ", e);
            throw new IllegalArgumentException();
        }
    }

    private List<List<String>> getBodyInCsvFile(MultipartFile file) {
        try {
            return new CsvFileParseUtil().getBody(file.getInputStream());
        } catch (IOException e) {
            log.error("CSV file body parsing error : ", e);
            throw new IllegalArgumentException();
        }
    }

    private List<List<HashMap<String ,String>>> makeQueryParamData(List<String> fileHeader, List<List<String>> fileBody) {
        int idIdx = fileHeader.indexOf("id");
        int firstnameIdx = fileHeader.indexOf("firstname");
        int lastnameIDx = fileHeader.indexOf("lastname");
        int emailIDx = fileHeader.indexOf("email");

        List<List<HashMap<String ,String>>> dataParam = new ArrayList<>();
        List<HashMap<String ,String>> multiData = new ArrayList<>();

        int batchCnt = -1;
        for(List<String> body : fileBody) {
            batchCnt ++;
            if (batchCnt == 0) continue;
            HashMap<String, String> saveData = new HashMap<>();
            saveData.put("id", body.get(idIdx));
            saveData.put("firstname", body.get(firstnameIdx));
            saveData.put("lastname", body.get(lastnameIDx));
            saveData.put("email", body.get(emailIDx));
            multiData.add(saveData);
            if (batchCnt % 10000 == 0) {
                dataParam.add(multiData);
                multiData = new ArrayList<>();
            }
        }

        return dataParam;
    }
}
