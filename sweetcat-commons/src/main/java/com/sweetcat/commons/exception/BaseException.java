package com.sweetcat.commons.exception;

import lombok.Data;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/15-20:50
 * @Version: 1.0
 */
public class BaseException extends RuntimeException{
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }
}
