package com.sweetcat.takewayorder.domain.order.entity;

import lombok.Getter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-21:17
 * @version: 1.0
 */
@Getter
public class StoreInfo implements Cloneable{
    private Long orderId;
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
    /**
     * 商家地址
     */
    private AddressInfoOfStore addressInfo;

    public StoreInfo(Long orderId, Long storeId) {
        this.orderId = orderId;
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

    public void setAddressInfo(AddressInfoOfStore addressInfo) {
        this.addressInfo = addressInfo;
    }

    @Override
    public StoreInfo clone() throws CloneNotSupportedException {
        return ((StoreInfo) super.clone());
    }
}
