package com.sweetcat.storecommodity.application.event.subscribe;

import com.sweetcat.commons.domainevent.userfavorite.AddedCommodityToFavoriteEvent;
import com.sweetcat.storecommodity.application.service.CommodityInfoApplicationService;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/29-13:02
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat_user_favorite",
        selectorType = SelectorType.TAG,
        selectorExpression = "add_commodity_to_favorite",
        consumeMode = ConsumeMode.ORDERLY,
        consumerGroup = "CG_sweetcat_store_commodity_add_commodity_to_favorite"
)
public class AddCommodityToFavoriteEventSubscriber implements RocketMQListener<AddedCommodityToFavoriteEvent> {
    private CommodityInfoApplicationService commodityApplicationService;

    @Autowired
    public void setCommentApplicationoService(CommodityInfoApplicationService commodityApplicationService) {
        this.commodityApplicationService = commodityApplicationService;
    }

    @Override
    public void onMessage(AddedCommodityToFavoriteEvent event) {
        System.out.println("sweetcat-store-commodity 接收领域事件 AddCommodityToFavoriteEvent 时间为：" + Instant.now().toEpochMilli());
        commodityApplicationService.increCommodityCollectNumber(event.getCommodityId(), 1);
    }
}
