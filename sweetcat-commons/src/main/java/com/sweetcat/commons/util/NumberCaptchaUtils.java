package com.sweetcat.commons.util;

import org.junit.Test;

import java.time.Instant;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-18:47
 * @Version: 1.0
 */
public class NumberCaptchaUtils {
    public static String generate(Integer length) {
        long timeStamp = Instant.now().toEpochMilli();
        int random = (int) (Math.random() + 1);
        return Integer.toString((int) (timeStamp * random % Math.pow(10, length)));
    }

}
