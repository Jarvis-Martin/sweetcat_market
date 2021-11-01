package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-20:42
 * @Version: 1.0
 */
public class RedisSaveFailException extends BaseException{
    public RedisSaveFailException(String code, String message) {
        super(code, message);
    }
}
