package com.sweetcat.commons.exception;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/9-19:14
 * @version: 1.0
 */
public class CommodityNotExistedException extends BaseException{
    public CommodityNotExistedException(String code, String message) {
        super(code, message);
    }
}
