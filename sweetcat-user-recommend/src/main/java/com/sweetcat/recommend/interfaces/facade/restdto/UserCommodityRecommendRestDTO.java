package com.sweetcat.recommend.interfaces.facade.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/12-20:05
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCommodityRecommendRestDTO {
    /**
     * 记录id
     */
    private String recordId;

    /**
     * 推荐人
     */
    private HashMap<String, Object> referrer;

    /**
     * 商品id
     */
    private HashMap<String, Object> commodity;

    /**
     * 推荐原因
     */
    private String reason;

    /**
     * 推荐时配图
     */
    private List<String> commodityPics;

    /**
     * 记录创建时间
     */
    private Long createTime;
}
