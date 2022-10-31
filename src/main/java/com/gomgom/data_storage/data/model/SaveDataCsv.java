package com.gomgom.data_storage.data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SaveDataCsv {
    // File
    @ApiModelProperty(value = "string", dataType = "file", example = "100")
    private MultipartFile fileName;

    // File
    @ApiModelProperty(value = "string", dataType = "file", example = "100")
    private MultipartFile contentType;

    // File
    @ApiModelProperty(value = "string", dataType = "file", example = "100")
    private MultipartFile size;
}
