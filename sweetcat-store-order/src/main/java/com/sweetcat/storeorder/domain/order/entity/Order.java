package com.sweetcat.storeorder.domain.order.entity;

import com.sweetcat.commons.ResponseStatusEnum;
import com.sweetcat.commons.exception.ParameterFormatIllegalityException;
import lombok.Getter;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/4-21:33
 * @version: 1.0
 */
@Getter
public class Order {
    /**
     * 未支付
     */
    public static final Integer STATUS_UNPAY = 0;
    /**
     * 已支付
     */
    public static final Integer STATUS_PAIED = 1;
    /**
     * 已支付
     */
    public static final Integer STATUS_CANCELED = 2;
    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 商品信息
     */
    private List<CommodityInfo> commodityInfoList;
    /**
     * 时间信息
     */
    private TimeInfo timeInfo;
    /**
     * 用户信息
     */
    private UserInfo userInfo;
    /**
     * 金额信息
     */
    private AmountInfoOfOrder amountInfo;

    public Order(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setCommodityInfoList(List<CommodityInfo> commodityInfoList) {
        this.commodityInfoList = commodityInfoList;
    }

    public void setTimeInfo(TimeInfo timeInfo) {
        this.timeInfo = timeInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void setAmountInfo(AmountInfoOfOrder amountInfo) {
        this.amountInfo = amountInfo;
    }

    public void changeUserAddress(AddressInfo addressInfo) {
        UserInfo userInfoCloned = null;
        try {
            userInfoCloned = this.userInfo.clone();
            userInfoCloned.setAddressInfo(addressInfo);
            this.setUserInfo(userInfoCloned);
        } catch (CloneNotSupportedException e) {
            throw new ParameterFormatIllegalityException(
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorCode(),
                    ResponseStatusEnum.PARAMETERFORMATILLEGALITY.getErrorMessage()
            );
        }
    }
}
