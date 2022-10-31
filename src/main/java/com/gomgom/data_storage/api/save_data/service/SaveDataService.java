package com.gomgom.data_storage.api.save_data.service;

import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestString;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateResult;
import com.gomgom.data_storage.data.mapper.SaveDataMapper;
import com.gomgom.data_storage.data.model.SaveDataString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        log.debug("gomgom files", files);
        System.out.println(files);
        for(MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
//            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
            System.out.println(fileName);
        }
//
//        UploadFile record = new UploadFile();
//        record.setName(fileName);
//        record.setFilePath(filePath + "/"+ folderPath+"/"+ randomName + "." + fileNameExtension);
//        log.debug("######## filePath : {}", record.getFilePath());
//        uploadFileMapper.insertSelective(record);
//
//        UploadFileResponse response = new UploadFileResponse();
//        response.setResult(true);
//        response.setIdx(record.getIdx());
//        response.setFilename(fileName);

        return SaveDataCreateResult.SUCCESS;
    }

}
