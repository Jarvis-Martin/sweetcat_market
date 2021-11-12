package com.sweetcat.recommend.application.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-19:35
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommodityRecommendCommand {

    /**
     * 用户id
     */
    private Long userId;

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
}
