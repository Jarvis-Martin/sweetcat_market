package com.sweetcat.favorite.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-10:50
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavoriteDTO {
    private String favoriteId;

    private String userId;

    /**
     * 商品id
     */
    private String commodityId;

    /**
     * 商品正面图（small）
     */
    private String picSmall;

    /**
     * 商品名
     */
    private String commodityName;

    /**
     * 现价
     */
    private BigDecimal currentPrice;

    /**
     * 收藏时价格
     */
    private BigDecimal priceWhenFavorite;

    /**
     * 收藏时间
     */
    private Long createTime;

    /**
     * 加购数
     */
    private Integer addToCarNumber;

    /**
     * 金币可抵扣金额
     */
    private BigDecimal coinCounteractNumber;

    /**
     * 购后反馈金币数
     */
    private Long feedbackCoinNumber;
}
