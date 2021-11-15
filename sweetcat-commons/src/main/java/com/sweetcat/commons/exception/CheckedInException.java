package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-23:11
 * @Version: 1.0
 */
public class CheckedInException extends BaseException{
    public CheckedInException(String code, String message) {
        super(code, message);
    }
}
