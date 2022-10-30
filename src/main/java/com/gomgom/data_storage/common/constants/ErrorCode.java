package com.gomgom.data_storage.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    COMMON_SERVER_ERROR("E10001"),
    COMMON_BAD_REQUEST("E10002"),
    COMMON_UNAUTHORIZED("E10003"),
    COMMON_HTTP_ERROR("E10004");

    private final String code;

    private ErrorCode(String code) {
        this.code = code;
    }
}
