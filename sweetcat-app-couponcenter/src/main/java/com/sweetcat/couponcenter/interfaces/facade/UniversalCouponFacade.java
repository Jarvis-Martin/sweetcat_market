package com.sweetcat.couponcenter.interfaces.facade;

import com.sweetcat.couponcenter.application.command.AddUniversalCouponCommand;
import com.sweetcat.couponcenter.application.service.UniversalCouponApplicationService;
import com.sweetcat.couponcenter.domain.coupon.entity.UniversalCoupon;
import com.sweetcat.couponcenter.domain.coupon.vo.CouponTargetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Coder_Jarvis
 * @description:
 * @date: 2021-11-2021/11/20-21:55
 * @version: 1.0
 */
@Component
public class UniversalCouponFacade {
    private UniversalCouponApplicationService universalCouponApplicationService;

    @Autowired
    public void setUniversalCouponApplicationService(UniversalCouponApplicationService universalCouponApplicationService) {
        this.universalCouponApplicationService = universalCouponApplicationService;
    }

    /**
     * 查询分页数据
     *
     * @param page
     * @param limit
     * @return
     */
    public List<UniversalCoupon> findPage(Integer page, Integer limit) {
        return universalCouponApplicationService.findPage(CouponTargetType.TARGETTYPE_UNIVERSAL, page, limit);
    }

    public void addOne(AddUniversalCouponCommand command) {
        universalCouponApplicationService.addOne(command);
    }

}
