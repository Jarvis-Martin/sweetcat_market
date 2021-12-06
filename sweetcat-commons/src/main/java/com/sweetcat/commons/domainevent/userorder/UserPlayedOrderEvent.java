package com.sweetcat.commons.domainevent.userorder;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-12-2021/12/6-16:47
 * @version: 1.0
 */
public class UserPlayedOrderEvent {
    private Long domainEventId;
    private Long occurOn;
    private Long userId;
    private Long orderId;
    private Long AddressId;
    private List<Long> couponIdsForOrder;
    private List<CommodityCouponMap> commodityCouponMaps;
}
