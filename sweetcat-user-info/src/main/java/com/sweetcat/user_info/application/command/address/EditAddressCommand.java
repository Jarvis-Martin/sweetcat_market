package com.sweetcat.user_info.application.command.address;

import com.sweetcat.user_info.domain.address.exception.UserAddressPropertyException;

import java.time.Instant;
import java.util.regex.Pattern;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-10-2021/10/31-11:57
 * @Version: 1.0
 */
public class EditAddressCommand {
    private Long addressId;
    /**
     * 地址所属用户的 id
     */
    private Long userId;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人手机号
     */
    private String receiverPhone;

    /**
     * 省名
     */
    private String provinceName;

    /**
     * 市名
     */
    private String cityName;

    /**
     * 区名
     */
    private String areaName;

    /**
     * 街道名
     */
    private String townName;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 默认地址？0：是默认地址；1：非默认地址,用户首次添加的地址设置为默认地址
     */
    private Integer defaultAddress;

    /**
     * 创建时间
     */
    private Long updateTime;

    public EditAddressCommand(Long addressId, Long userId, String receiverName, String receiverPhone, String provinceName,
                             String cityName, String areaName, String townName, String detailAddress,
                             Integer defaultAddress, Long updateTime) {
        this.addressId = addressId;
        this.userId = userId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.areaName = areaName;
        this.townName = townName;
        this.detailAddress = detailAddress;
        this.defaultAddress = defaultAddress;
        this.updateTime = updateTime;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getTownName() {
        return townName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public Integer getDefaultAddress() {
        return defaultAddress;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    private void setAddressId(Long addressId) {
        if (addressId == null || addressId < 0) {
            throw new UserAddressPropertyException("收货地址所属地址编号格式错误");
        }
        this.addressId = addressId;
    }

    private void setUserId(Long userId) {
        if (userId == null || userId < 0) {
            throw new UserAddressPropertyException("收货地址所属用户编号格式错误");
        }
        this.userId = userId;
    }

    private void setReceiverName(String receiverName) {
        if (receiverName == null || receiverName.length() <= 0) {
            throw new UserAddressPropertyException("收货人姓名不能为空");
        }
        this.receiverName = receiverName;
    }

    private void setReceiverPhone(String receiverPhone) {
        // 手机号为 null，通知用户参数格式错误
        if (receiverPhone == null) {
            throw new UserAddressPropertyException("收货人手机号不能为空");
        }
        // 正则验证手机号格式
        String phoneRegex = "^1[3-9]\\d{9}$";
        boolean phoneMatches = Pattern.matches(phoneRegex, receiverPhone);
        // phone 格式不匹配，通知用户参数格式错误
        if (!phoneMatches) {
            throw new UserAddressPropertyException("收货人手机号格式错误");
        }
        this.receiverPhone = receiverPhone;
    }

    private void setProvinceName(String provinceName) {
        if (provinceName == null || provinceName.length() <= 0) {
            throw new UserAddressPropertyException("收货地址省名称不能为空");
        }
        this.provinceName = provinceName;
    }

    private void setCityName(String cityName) {
        if (cityName == null || cityName.length() <= 0) {
            throw new UserAddressPropertyException("收货地址市名称不能为空");
        }
        this.cityName = cityName;
    }

    private void setAreaName(String areaName) {
        if (areaName == null || areaName.length() <= 0) {
            throw new UserAddressPropertyException("收货地址区名称不能为空");
        }
        this.areaName = areaName;
    }

    private void setTownName(String townName) {
        if (townName == null || townName.length() <= 0) {
            throw new UserAddressPropertyException("收货地址街道名称不能为空");
        }
        this.townName = townName;
    }

    private void setDetailAddress(String detailAddress) {
        if (detailAddress == null || detailAddress.length() <= 0) {
            throw new UserAddressPropertyException("详细收货地址不能为空");
        }
        this.detailAddress = detailAddress;
    }

    private void setDefaultAddress(Integer defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    private void setUpdateTime(Long updateTime) {
        long milli = Instant.now().toEpochMilli();
        this.updateTime = updateTime < 0 || updateTime > milli ? 0 : milli;
    }
}
