package com.sweetcat.api.rpcdto.commodityinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/11-16:51
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityInfoRpcDTO {
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
     * 修改时间
     */
    private Long updateTime;

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
     * 金币抵扣率,取值范围：0% ~ 99%
     */
    private BigDecimal coinCounteractRate;

    /**
     * 金币可抵扣金额
     */
    private BigDecimal coinCounteractNumber;

    /**
     * 购后反馈金币数
     */
    private Long feedbackCoinNumber;

}
