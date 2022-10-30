package com.gomgom.data_storage.api.save_data.controller;

import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestCsv;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestString;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateResult;
import com.gomgom.data_storage.api.save_data.service.SaveDataService;
import com.gomgom.data_storage.common.constants.ErrorCode;
import com.gomgom.data_storage.common.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/saveData")
public class SaveDataController {

    @Autowired
    SaveDataService saveDataService;

    @RequestMapping(value = "/create-string", method = RequestMethod.POST, produces = "application/json")
    public SaveDataCreateResult createSaveData(@RequestBody SaveDataCreateRequestString request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new BasicException(ErrorCode.COMMON_BAD_REQUEST);
        return saveDataService.createSaveDataString(request);
    }

    @RequestMapping(value = "/create-csv", method = RequestMethod.POST, produces = "application/json")
    public SaveDataCreateResult createSaveData(@RequestBody SaveDataCreateRequestCsv request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new BasicException(ErrorCode.COMMON_BAD_REQUEST);
        return saveDataService.createSaveDataCsv(request);
    }

}
