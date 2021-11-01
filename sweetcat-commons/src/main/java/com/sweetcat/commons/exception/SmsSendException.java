package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-19:02
 * @Version: 1.0
 */
public class SmsSendException extends BaseException{
    public SmsSendException(String code, String message) {
        super(code, message);
    }
}
