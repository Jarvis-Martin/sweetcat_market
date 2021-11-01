package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-22:58
 * @Version: 1.0
 */
public class CaptchaTimeoutException extends BaseException{
    public CaptchaTimeoutException(String code, String message) {
        super(code, message);
    }
}
