package com.sweetcat.api.rpcdto.storeorder;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/2-20:41
 * @version: 1.0
 */
@Data
public class StoreRpcDTO {
    /**
     * 商家id
     */
    private Long storeId;
    /**
     * 商家名
     */
    private String storeName;
    /**
     * 商家logo
     */
    private String storeLogo;

    public StoreRpcDTO(Long storeId) {
        this.storeId = storeId;
    }
}
