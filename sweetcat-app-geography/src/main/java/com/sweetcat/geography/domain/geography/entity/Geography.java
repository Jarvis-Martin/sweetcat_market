package com.sweetcat.geography.domain.geography.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * t_app_geography
 * @author 
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Geography implements Serializable {
    /**
     * 地区码
     */
    private String addressCode;

    /**
     * 地区名
     */
    private String addressName;

    /**
     * 该地址对应的省码
     */
    private String provinceCode;

    /**
     * 该地址对应的市码
     */
    private String cityCode;

    /**
     * 该地址对应的区码
     */
    private String areaCode;

    /**
     * 该地址对应的县码
     */
    private String townCode;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updateTime;

    public void setAddressCode(String addressCode) {
        if (addressCode == null || addressCode.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.addressCode = addressCode;
    }

    public void setAddressName(String addressName) {
        if (addressName == null || addressName.length() < 0) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
        this.addressName = addressName;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
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