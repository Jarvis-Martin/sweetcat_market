package com.sweetcat.userorder.domain.order.entity;

import lombok.Getter;
import org.apache.catalina.Store;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/30-17:18
 * @version: 1.0
 */
@Getter
public class StoreInfo implements Cloneable{
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

    public StoreInfo(Long orderId, Long storeId) {
        this.setOrderId(orderId);
        this.storeId = storeId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    @Override
    public StoreInfo clone() throws CloneNotSupportedException {
        return ((StoreInfo) super.clone());
    }
}
