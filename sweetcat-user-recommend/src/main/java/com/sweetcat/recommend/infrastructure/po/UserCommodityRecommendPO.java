package com.sweetcat.recommend.infrastructure.po;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * t_user_commodity_recommend
 * @author 
 */
@Data
public class UserCommodityRecommendPO implements Serializable {
    /**
     * 记录id
     */
    private Long recordId;

    /**
     * 用户id
     */
    private Long referrerId;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 推荐原因
     */
    private String reason;

    /**
     * 推荐星级,范围 0 ~ 5
     */
    private Integer star;

    /**
     * 推荐时配图
     */
    private List<String> commodityPics;

    /**
     * 商品规格
     */
    private Object commoditySpecification;

    /**
     * 创建时间
     */
    private Long createTime;

    private static final long serialVersionUID = 1L;
}