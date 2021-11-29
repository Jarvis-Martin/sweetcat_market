package com.sweetcat.storecommodity.application.event.subscribe;

import com.sweetcat.commons.domainevent.trolley.RemovedCommodityFromTrolleyEvent;
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
 * @date: 2021-11-2021/11/29-11:08
 * @version: 1.0
 */
@Component
@RocketMQMessageListener(
        topic = "sweetcat_user_trolley",
        selectorType = SelectorType.TAG,
        selectorExpression = "remove_commodity_from_trolley",
        consumeMode = ConsumeMode.ORDERLY,
        consumerGroup = "CG_sweetcat_store_commodity_remove_commodity_from_trolley"
)
public class RemovedCommodityFromTrolleyEventSubscriber implements RocketMQListener<RemovedCommodityFromTrolleyEvent> {
    private CommodityInfoApplicationService commodityApplicationService;

    @Autowired
    public void setCommentApplicationoService(CommodityInfoApplicationService commodityApplicationService) {
        this.commodityApplicationService = commodityApplicationService;
    }

    @Override
    public void onMessage(RemovedCommodityFromTrolleyEvent event) {
        System.out.println("sweetcat-store-commodity 接收领域事件 RemovedCommodityFromTrolleyEvent 时间为：" + Instant.now().toEpochMilli());
        commodityApplicationService.increAddCommodityToCartNumber(event.getCommodityId(), -1);
    }
}
