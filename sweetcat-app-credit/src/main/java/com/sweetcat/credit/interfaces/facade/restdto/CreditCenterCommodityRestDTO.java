package com.sweetcat.credit.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/17-20:43
 * @version: 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCenterCommodityRestDTO {
    /**
     * 积分商城像编号
     */
    private Long marketItemId;

    /**
     * 创建人编号
     */
    private Long creatorId;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 库存
     */
    private Long stock;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 兑换商品所需积分数
     */
    private Long creditNumber;

    /**
     * 商品类别：0优惠券；1实物商品
     */
    private Integer commodityType;

    public CreditCenterCommodityRestDTO(Long marketItemId) {
        this.marketItemId = marketItemId;
    }
}
