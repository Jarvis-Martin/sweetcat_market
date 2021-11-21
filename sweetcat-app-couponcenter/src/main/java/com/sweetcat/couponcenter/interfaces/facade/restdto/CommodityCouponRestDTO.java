package com.sweetcat.couponcenter.interfaces.facade.restdto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-22:02
 * @version: 1.0
 */
@Getter
@Setter
public class CommodityCouponRestDTO extends CouponRestDTO implements Serializable {
    /**
     * 目标优惠券id
     */
    private Long couponId;

    /**
     * 优惠券使用对象类别表示；0通用券，1商品券
     */
    private Integer targetType;

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
     * 商品正面图（small） 3张最佳
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


    public CommodityCouponRestDTO(Long couponId, BigDecimal thresholdPrice, BigDecimal counteractPrice, Long stock) {
        super(couponId, thresholdPrice, counteractPrice, stock);
        this.couponId = couponId;
    }
}
