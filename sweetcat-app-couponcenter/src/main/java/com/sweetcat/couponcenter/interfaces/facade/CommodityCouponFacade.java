package com.sweetcat.couponcenter.interfaces.facade;

import com.sweetcat.couponcenter.application.command.AddCommodityCouponCommand;
import com.sweetcat.couponcenter.application.service.CommodityCouponApplicationService;
import com.sweetcat.couponcenter.domain.coupon.entity.CommodityCoupon;
import com.sweetcat.couponcenter.domain.coupon.vo.CouponTargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:53
 * @version: 1.0
 */
@Component
public class CommodityCouponFacade {
    private CommodityCouponApplicationService commodityCouponApplicationService;

    @Autowired
    public void setCommodityCouponApplicationService(CommodityCouponApplicationService commodityCouponApplicationService) {
        this.commodityCouponApplicationService = commodityCouponApplicationService;
    }

    /**
     * 查询分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    public List<CommodityCoupon> findPage(Integer page, Integer limit) {
        return commodityCouponApplicationService.findPage(CouponTargetType.TARGETTYPE_COMMODITY, page, limit);
    }

    public void addOne(AddCommodityCouponCommand command) {
        commodityCouponApplicationService.addOne(command);
    }
}
