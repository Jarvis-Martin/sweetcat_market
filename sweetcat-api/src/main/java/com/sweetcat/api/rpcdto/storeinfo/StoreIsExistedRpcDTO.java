package com.sweetcat.api.rpcdto.storeinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/8-16:11
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreIsExistedRpcDTO {
    private String storeId;
    private Boolean isExisted;
    private Long timeStamp;

    public Boolean isExisted() {
        return isExisted;
    }
}
