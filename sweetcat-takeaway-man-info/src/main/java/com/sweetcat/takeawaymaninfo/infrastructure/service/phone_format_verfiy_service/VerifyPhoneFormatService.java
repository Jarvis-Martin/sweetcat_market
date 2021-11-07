package com.sweetcat.takeawaymaninfo.infrastructure.service.phone_format_verfiy_service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/31-14:36
 * @Version: 1.0
 */
@Service
public class VerifyPhoneFormatService {

    /**
     * 检查手机格式：null？ 格式？
     *
     * @param phones phones
     */
    public void verifyPhoneFormat(String ...phones) {
        Arrays.stream(phones).forEach(phone -> {
        // 手机号为 null，通知用户参数格式错误
            if (phone == null) {
                throw new ParameterFormatIllegalityException(
                        ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                        ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
                );
            }
            // 正则验证手机号格式
            String phoneRegex = "^1[3-9]\\d{9}$";
            boolean phoneMatches = Pattern.matches(phoneRegex, phone);
            // phone 格式不匹配，通知用户参数格式错误
            if (!phoneMatches) {
                throw new ParameterFormatIllegalityException(
                        ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                        ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
                );
            }
        });
    }
}
