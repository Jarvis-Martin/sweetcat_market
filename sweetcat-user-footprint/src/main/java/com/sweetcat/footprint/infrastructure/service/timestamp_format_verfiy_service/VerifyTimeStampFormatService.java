package com.sweetcat.footprint.infrastructure.service.timestamp_format_verfiy_service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/31-21:01
 * @Version: 1.0
 */
@Service
public class VerifyTimeStampFormatService {
    /**
     * 验证 id 是否为 null 或 小于0
     *
     * @param times times
     */
    public void verifyTimeStamps(Long... times) {
        Arrays.stream(times).forEach(
                timeStamp -> {
                    if (timeStamp == null || timeStamp < 0) {
                        throw new ParameterFormatIllegalityException(
                                ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                                ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
                        );
                    }
                }
        );
    }
}
