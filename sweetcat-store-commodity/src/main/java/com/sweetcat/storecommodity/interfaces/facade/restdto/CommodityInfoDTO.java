package com.sweetcat.storecommodity.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: Coder_Jarvis
 * @Description:
 * @Date: 2021-11-2021/11/7-22:59
 * @Version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommodityInfoDTO {
    /**
     * 商品od
     */
    private String commodityId;

    /**
     * 店铺id
     */
    private String storeId;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 商品正面图（small）
     */
    private String picSmall;

    /**
     * 商品主图（big）
     */
    private List<String> picBig;

    /**
     * 一级分类
     */
    private String firstCategory;

    /**
     * 二级分类
     */
    private String secondCategory;

    /**
     * 三级分类
     */
    private String thirdCategory;

    /**
     * 几手货.0：全新；1：二手货
     */
    private Integer useTimes;

    /**
     * 产地
     */
    private String productionArea;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 宝贝描述
     */
    private String description;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 月销量
     */
    private Integer monthlySales;

    /**
     * 加购数
     */
    private Integer addToCarNumber;

    /**
     * 收藏数
     */
    private Integer collectNumber;

    /**
     * 保障
     */
    private String guarantee;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 商品状态.0： 待审核; 1： 审核通过; 2： 审核未通过; 3：已下架
     */
    private Integer status;

    /**
     * 商品规格
     */
    private String specification;

    /**
     * 评论总数
     */
    private Long commentNumber;

    /**
     * 默认运费
     */
    private BigDecimal defaultPostCharge;

    /**
     * 商品多余一件时，多出的商品每件多少运费
     */
    private BigDecimal subjoinChargePerGood;

    /**
     * 金币可抵扣金额
     */
    private BigDecimal coinCounteractNumber;

    /**
     * 购后反馈金币数
     */
    private Long feedbackCoinNumber;
}
