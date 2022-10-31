package com.gomgom.data_storage.api.save_data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SaveDataCreateRequestCsv {
    @ApiModelProperty(value = "file", dataType = "file", example = "100")
    private MultipartFile file;
}
