package com.sweetcat.credit.application.command;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/18-19:15
 * @version: 1.0
 */
@Data
public class AddBaseCommodityCommand {

    private Long marketItemId;
    /**
     * 创建人id：如店家id
     */
    private Long creatorId;

    /**
     * 创建人名称：如店家名
     */
    private String creatorName;

    /**
     * 商品库存
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
}
