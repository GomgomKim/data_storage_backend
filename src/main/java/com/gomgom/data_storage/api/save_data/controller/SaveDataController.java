package com.gomgom.data_storage.api.save_data.controller;

import com.gomgom.data_storage.api.save_data.model.SaveDataCreateRequestString;
import com.gomgom.data_storage.api.save_data.model.SaveDataCreateResult;
import com.gomgom.data_storage.api.save_data.service.SaveDataService;
import com.gomgom.data_storage.common.constants.ErrorCode;
import com.gomgom.data_storage.common.exception.BasicException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@CrossOrigin("*")
@RestController
@RequestMapping("/saveData")
@Api(tags = {"파일 업로드"})
public class SaveDataController {

    @Autowired
    SaveDataService saveDataService;


    @RequestMapping(value = "/create-string", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SaveDataCreateResult createSaveDataString(@RequestBody SaveDataCreateRequestString request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) throw new BasicException(ErrorCode.COMMON_BAD_REQUEST);
        return saveDataService.createSaveDataString(request);
    }

    @ApiOperation(value = "파일 업로드", notes = "파일을 업로드한다")
    @RequestMapping(value = "/create-csv", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SaveDataCreateResult createSaveDataCsv(@RequestParam("file") MultipartFile[] files) throws Exception {
        return saveDataService.createSaveDataCsv(files);
    }

    @GetMapping(value = "/connect/{id}", produces = "text/event-stream")
    public SseEmitter connect(@PathVariable String id) {
        return saveDataService.getUploadStatus(id);
    }


}
