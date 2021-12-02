package com.sweetcat.storeinfo.interfaces.facade.restdto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-17:33
 * @Version: 1.0
 */
@Getter
@Setter
public class StoreInfoDTO {
    /**
     * 商家编号
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

    public StoreInfoDTO(Long storeId) {
        this.storeId = storeId;
    }

    public StoreInfoDTO(Long storeId, String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.principalName = principalName;
        this.principalPhone = principalPhone;
        this.mainBusiness = mainBusiness;
        this.type = type;
        this.createTime = createTime;
    }
}
