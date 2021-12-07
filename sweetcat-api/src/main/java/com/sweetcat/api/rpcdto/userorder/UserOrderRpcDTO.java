package com.sweetcat.api.rpcdto.userorder;

import lombok.Data;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/7-13:27
 * @version: 1.0
 */
@Data
public class UserOrderRpcDTO {
    /**
     * 订单id
     */
    private Long orderId;
    private Integer orderStatus;
    /**
     * 商品信息
     */
    private List<CommodityInfoRpcDTO> commodityInfoRpcDTOList;
    /**
     * 时间信息
     */
    private TimeInfoRpcDTO timeInfoRpcDTO;
    /**
     * 用户信息
     */
    private UserInfoRpcDTO userInfoRpcDTO;

    private StoreRpcDTO storeRpcDTO;
    /**
     * 金额信息
     */
    private AmountInfoRpcDTO amountInfoRpcDTO;
}
