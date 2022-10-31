package com.gomgom.data_storage.api.save_data.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SaveDataCreateRequestString {
    // id값
    @ApiModelProperty(value = "ID", dataType = "String", example = "100")
    private String id;

    // 성
    @ApiModelProperty(value = "First name", dataType = "String", example = "Nanete")
    private String firstname;

    // 이름
    @ApiModelProperty(value = "Last name", dataType = "String", example = "Yusuk")
    private String lastname;

    // 이메일
    @ApiModelProperty(value = "Email", dataType = "String", example = "Nanete.Yusuk@yopmail.com")
    private String email;
}
