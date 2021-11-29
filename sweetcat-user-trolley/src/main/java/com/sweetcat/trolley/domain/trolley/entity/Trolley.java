package com.sweetcat.trolley.domain.trolley.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-14:45
 * @version: 1.0
 */
@Getter
public class Trolley {
    /**
     * 购物车的唯一标识
     */
    private Long trolleyId;
    /**
     * 购物车内商品的key
     */
    private List<String> commodityKeys;

    public void addOneCommodity(String key) {
        this.commodityKeys.add(key);
    }

    public Trolley(Long trolleyId) {
        this.setTrolleyId(trolleyId);
    }

    public void setTrolleyId(Long trolleyId) {
        if (trolleyId == null || trolleyId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.trolleyId = trolleyId;
    }

    public void setCommodityKeys(List<String> commodityKeys) {
        this.commodityKeys = commodityKeys;
    }
}
