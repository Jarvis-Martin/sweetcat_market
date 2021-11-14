package com.sweetcat.customerservice.infrastructure.service.id_format_verfiy_service;

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
public class VerifyIdFormatService {
    /**
     * 验证 id 是否为 null 或 小于0
     *
     * @param ids id
     */
    public void verifyIds(Long... ids) {
        Arrays.stream(ids).forEach(
                id -> {
                    if (id == null || id < 0) {
                        throw new ParameterFormatIllegalityException(
                                ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                                ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
                        );
                    }
                }
        );
    }
}
