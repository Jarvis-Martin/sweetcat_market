package com.sweetcat.commons.domainevent.trolley;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/29-11:04
 * @version: 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class AddedCommodityToTrolleyEvent {
    /**
     * 事件唯一id
     */
    private Long domainEventId;
    /**
     * 事件触发时间
     */
    private Long occurOn;
    /**
     * 发起加购操作的userId
     */
    private Long userId;
    /**
     * 被加购商品的id
     */
    private Long commodityId;

    public AddedCommodityToTrolleyEvent(Long domainEventId) {
        this.domainEventId = domainEventId;
    }

}
