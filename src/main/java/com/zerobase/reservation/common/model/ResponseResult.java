package com.zerobase.reservation.common.model;

import lombok.Data;

// 클라이언트에 내릴 목적
@Data
public class ResponseResult {

    ResponseResultHeader header;
    Object body;

    public ResponseResult(boolean result, String message) {
        header = new ResponseResultHeader(result, message);
    }

    public ResponseResult(boolean result) {
        header = new ResponseResultHeader(result);
    }
}
