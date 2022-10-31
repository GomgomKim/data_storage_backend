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

        log.debug("gomgom request :" + request);

        SaveDataCreateResult result = saveDataMapper.insertSelective(saveData) > 0 ? SaveDataCreateResult.SUCCESS : SaveDataCreateResult.FAIL;
        log.debug("gomgom result :" + result);

        return result;
    }

    public SaveDataCreateResult createSaveDataCsv(MultipartFile[] files) throws Exception {
        for(MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            List<String> fileHeader = getHeaderInCsvFile(file);
            List<List<String>> fileBody = getBodyInCsvFile(file);
//            System.out.println(fileBody);
//            System.out.println(fileName);
            System.out.println(fileHeader);
            System.out.println(fileHeader.indexOf("id"));
            System.out.println(fileHeader.indexOf("firstname"));
            System.out.println(fileHeader.indexOf("lastname"));
            System.out.println(fileHeader.indexOf("email"));
        }

        return SaveDataCreateResult.SUCCESS;
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

}
