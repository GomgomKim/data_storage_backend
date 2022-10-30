package com.gomgom.data_storage.api.save_data.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SaveDataCreateRequestCsv {
    private MultipartFile file;
}
