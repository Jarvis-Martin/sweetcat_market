package com.sweetcat.trolley.application.command;

import lombok.Data;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/28-21:24
 * @version: 1.0
 */
@Data
public class AddTrolleyCommodityCommand {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品的id
     */
    private Long commodityId;

    /**
     * 商品规格
     */
    private String specification;

    /**
     * 加购时间
     */
    private Long createTime;
}
