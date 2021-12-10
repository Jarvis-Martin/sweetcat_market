package com.sweetcat.api.rpcdto.userorder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:18
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class StoreRpcDTO implements Cloneable{
    private Long orderId;
    /**
     * 店铺id
     */
    private Long storeId;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺logo
     */
    private String storeLogo;

    private StoreAddressRpcDTO address;

    public StoreRpcDTO(Long orderId, Long storeId) {
        this.setOrderId(orderId);
        this.storeId = storeId;
    }

    @Override
    public StoreRpcDTO clone() throws CloneNotSupportedException {
        return ((StoreRpcDTO) super.clone());
    }
}
