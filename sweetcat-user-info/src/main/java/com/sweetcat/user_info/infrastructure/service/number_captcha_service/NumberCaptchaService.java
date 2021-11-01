package com.sweetcat.user_info.infrastructure.service.number_captcha_service;

import com.sweetcat.commons.util.NumberCaptchaUtils;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/26-18:55
 * @Version: 1.0
 */
public class NumberCaptchaService {
    /**
     * 生成指定 length 的 随机验证码
     * @param length length
     * @return length 的 随机验证码
     */
    public String generate(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(NumberCaptchaUtils.generate(length));
        int codeLength = stringBuilder.length();
        if (codeLength < 6) {
            for (int i = 0; i < 6 - codeLength; i++) {
                stringBuilder.append('0');
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        NumberCaptchaService numberCaptchaService = new NumberCaptchaService();
        System.out.println(numberCaptchaService.generate(6));
    }
}
