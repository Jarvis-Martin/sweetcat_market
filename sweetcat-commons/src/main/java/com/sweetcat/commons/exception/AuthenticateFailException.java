package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-14:44
 * @Version: 1.0
 */
public class AuthenticateFailException extends BaseException{
    public AuthenticateFailException(String code, String message) {
        super(code, message);
    }
}