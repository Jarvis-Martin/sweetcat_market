package com.sweetcat.storeinfo.infrastructure.po;

import java.io.Serializable;
import lombok.Data;

/**
 * t_store_info
 * @author 
 */
@Data
public class StoreInfoPO implements Serializable {
    /**
     * 商家编号
     */private Long storeId;

    /**
     * 商家名
     */
    private String storeName;

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

    /**
     * 修改时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;
}