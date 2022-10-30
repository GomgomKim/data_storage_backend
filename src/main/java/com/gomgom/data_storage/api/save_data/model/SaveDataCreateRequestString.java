package com.gomgom.data_storage.api.save_data.model;

import lombok.Data;

@Data
public class SaveDataCreateRequestString {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
