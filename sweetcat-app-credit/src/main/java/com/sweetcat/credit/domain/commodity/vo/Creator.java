package com.sweetcat.credit.domain.commodity.vo;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/14-18:01
 * @version: 1.0
 */
@Getter
public class Creator {
    /**
     * 创建人id：如店家id
     */
    private Long creatorId;
    /**
     * 创建人名称：如店家名
     */
    private String creatorName;

    public Creator(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setCreatorId(Long creatorId) {
        if (creatorId == null || creatorId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creatorId = creatorId;
    }

    public void setCreatorName(String creatorName) {
        if (creatorName == null || creatorName.length() <= 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.creatorName = creatorName;
    }
}
