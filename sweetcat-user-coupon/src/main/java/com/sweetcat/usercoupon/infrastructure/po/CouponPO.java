package com.sweetcat.usercoupon.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * t_user_coupon_coupon_info
 * @author 
 */
@Data
public class CouponPO implements Serializable {
    /**
     * 目标优惠券id
     */
    private Long couponId;

    /**
     * 商品对应的店家id
     */
    private Long storeId;

    /**
     * 商品对应的店家名称
     */
    private String storeName;

    /**
     * 商品编号
     */
    private Long commodityId;

    /**
     * 鍟嗗搧姝ｉ潰鍥撅紙small锛?3寮犳渶浣?
     */
    private List<String> commodityPicSmall;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 券的时间类型：0限时券；1区间券
     */
    private Integer timeType;

    /**
     * 获得时间
     */
    public Long obtainTime;

    /**
     * 显示券的有效时长，自领取时开始计算
     */
    private Long validDuration;

    /**
     * 区间券的可使用时间
     */
    private Long startTime;

    /**
     * 区间券的最后可使用时间
     */
    private Long deadline;

    private static final long serialVersionUID = 1L;
}