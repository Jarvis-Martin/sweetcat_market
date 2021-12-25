package com.sweetcat.api.rpcdto.storeinfo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-13:46
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class StoreInfoRpcDTO {
    /**
     * 商家编号
     */
    private Long storeId;

    /**
     * 商家名
     */
    private String storeName;

    /**
     * 商家名
     */
    private String storeLogo;

    /**
     * 负责人姓名
     */
    private String principalName;

    /**
     * 负责人手机
     */
    private String principalPhone;

    /**
     * 主要业务,如：主要：信阳毛尖
     */
    private String mainBusiness;

    /**
     * 店铺类型,0：网店；1：实体店
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Long createTime;

    public StoreInfoRpcDTO(Long storeId) {
        this.storeId = storeId;
    }

    public StoreInfoRpcDTO(Long storeId, String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.principalName = principalName;
        this.principalPhone = principalPhone;
        this.mainBusiness = mainBusiness;
        this.type = type;
        this.createTime = createTime;
    }
}
