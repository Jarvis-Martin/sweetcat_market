package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-23:03
 * @Version: 1.0
 */
public class UserNotExistedException extends BaseException{
    public UserNotExistedException(String code, String message) {
        super(code, message);
    }
}
