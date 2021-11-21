package com.sweetcat.api.rpcdto.storeinfo;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/21-13:46
 * @version: 1.0
 */
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

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
