package com.sweetcat.commons.exception;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/29-11:12
 * @Version: 1.0
 */
public class CaptchaSendedException extends BaseException{
    private String remainingTime;

    public String getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(String remainingTime) {
        this.remainingTime = remainingTime;
    }

    public CaptchaSendedException(String code, String message, String remainingTime) {
        super(code, message);
        this.remainingTime = remainingTime;
    }
}
