package com.sweetcat.user_relationship.infrastructure.service.id_format_verfiy_service;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/3-21:56
 * @Version: 1.0
 */
@Service
public class VerifyIdFormatService {
    /**
     * 验证 id 是否为 null 或 小于0
     *
     * @param ids id
     */
    public void verifyId(Long... ids) {
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
