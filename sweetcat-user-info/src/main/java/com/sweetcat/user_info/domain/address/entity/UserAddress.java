package com.sweetcat.user_info.domain.address.entity;

import com.sweetcat.user_info.domain.address.exception.UserAddressPropertyException;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.util.regex.Pattern;

/**
 * t_user_address
 *
 * @author
 */
@Getter
public class UserAddress implements Serializable {
    public static final Integer DEFAULT_ADDRESS = 1;
    public static final Integer NOT_DEFAULT_ADDRESS = 0;
    /**
     * 收货地址 id
     */
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
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    private static final long serialVersionUID = 1L;

    public UserAddress(Long addressId,
                       Long userId,
                       String receiverName,
                       String receiverPhone,
                       String provinceName,
                       String cityName,
                       String areaName,
                       String townName,
                       String detailAddress,
                       Integer defaultAddress,
                       Long createTime,
                       Long updateTime) {
        this.addressId = addressId;
        this.setUserId(userId);
        this.setReceiverName(receiverName);
        this.setReceiverPhone(receiverPhone);
        this.setProvinceName(provinceName);
        this.setCityName(cityName);
        this.setAreaName(areaName);
        this.setTownName(townName);
        this.setDetailAddress(detailAddress);
        this.setDefaultAddress(defaultAddress);
        this.setCreateTime(createTime);
        this.setUpdateTime(updateTime);
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

    private void setCreateTime(Long createTime) {
        long milli = Instant.now().toEpochMilli();
        this.createTime = createTime < 0 || createTime > milli ? 0 : milli;
    }

    private void setUpdateTime(Long updateTime) {
        long milli = Instant.now().toEpochMilli();
        this.updateTime = updateTime < 0 || updateTime > milli ? 0 : milli;
    }

    public void changeUserId(Long userId) {
        this.setUserId(userId);
    }

    public void changeReceiverName(String receiverName) {
        this.setReceiverName(receiverName);
    }

    public void changeReceiverPhone(String receiverPhone) {
        this.setReceiverPhone(receiverPhone);
    }

    public void changeProvinceName(String provinceName) {
        this.setProvinceName(provinceName);
    }

    public void changeCityName(String cityName) {
        this.setCityName(cityName);
    }

    public void changeAreaName(String areaName) {
        this.setAreaName(areaName);
    }

    public void changeTownName(String townName) {
        this.setTownName(townName);
    }

    public void changeDetailAddress(String detailAddress) {
        this.setDefaultAddress(defaultAddress);
    }

    public void changeToDefaultAddress() {
        this.setDefaultAddress(1);
    }

    public void changeToNotDefaultAddress() {
        this.setDefaultAddress(0);
    }

    public void changeUpdateTime(Long updateTime) {
        this.setUpdateTime(updateTime);
    }

    public boolean isDefault() {
        return this.defaultAddress.equals(DEFAULT_ADDRESS);
    }

    /**
     * 根据 newUserAddress 修改地址
     *
     * @param newUserAddress newUserAddress
     */
    public void update(UserAddress newUserAddress) {
        this.changeReceiverName(newUserAddress.getReceiverName());
        this.changeReceiverPhone(newUserAddress.getReceiverPhone());
        this.changeProvinceName(newUserAddress.getProvinceName());
        this.changeCityName(newUserAddress.getCityName());
        this.changeAreaName(newUserAddress.getAreaName());
        this.changeTownName(newUserAddress.getTownName());
        this.changeDetailAddress(newUserAddress.getDetailAddress());
        if (newUserAddress.isDefault()) {
            this.changeToDefaultAddress();
        } else {
            this.changeToNotDefaultAddress();
        }
        this.changeUpdateTime(newUserAddress.getUpdateTime());
    }
}