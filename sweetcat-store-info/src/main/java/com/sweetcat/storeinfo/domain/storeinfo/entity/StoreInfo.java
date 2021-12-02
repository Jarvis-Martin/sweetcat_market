package com.sweetcat.storeinfo.domain.storeinfo.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * t_store_info
 *
 * @author
 */
@Getter
public class StoreInfo implements Serializable {
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

    /**
     * 修改时间
     */
    private Long updateTime;

    public StoreInfo(Long storeId) {
        this.setStoreId(storeId);
    }

    public StoreInfo(Long storeId, String storeName, String principalName, String principalPhone, String mainBusiness, Integer type, Long createTime, Long updateTime) {
        this.setStoreId(storeId);
        this.setStoreName(storeName);
        this.setPrincipalName(principalName);
        this.setPrincipalPhone(principalPhone);
        this.setMainBusiness(mainBusiness);
        this.setType(type);
        this.setCreateTime(createTime);
        this.setUpdateTime(updateTime);
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        if (storeId == null || storeId < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public void setStoreName(String storeName) {
        if (storeName == null || storeName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.storeName = storeName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        if (principalName == null || principalName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.principalName = principalName;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        // 正则验证手机号格式
        String phoneRegex = "^1[3-9]\\d{9}$";
        boolean phoneMatches = Pattern.matches(phoneRegex, principalPhone);
        // phone 格式不匹配，通知用户参数格式错误
        if (!phoneMatches) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.principalPhone = principalPhone;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        if (mainBusiness == null || mainBusiness.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.mainBusiness = mainBusiness;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        if (type == null || type < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        if (createTime == null || createTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        if (updateTime == null || updateTime < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.updateTime = updateTime;
    }

    private static final long serialVersionUID = 1L;
}